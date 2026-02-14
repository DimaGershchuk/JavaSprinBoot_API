package com.example.demo.Controller;

import com.example.demo.DTO.BudgetStatus;
import com.example.demo.Entity.Expense;
import com.example.demo.Entity.Period;
import com.example.demo.Service.ExpenseService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense createExpense(
            Authentication authentication,
            @RequestParam Long categoryId,
            @RequestParam Double amount,
            @RequestParam(required = false) String description
    ) {
        String username = authentication.getName();

        return expenseService.createExpense(
                username,
                categoryId,
                amount,
                description

        );
    }

    @GetMapping
    public List<Expense> getUserExpenses(Authentication authentication) {

        String username = authentication.getName();
        return expenseService.getUserExpenses(username);
    }

    @GetMapping("/budget-status")
    public BudgetStatus getBudgetStatus(Authentication authentication, @RequestParam(defaultValue = "MONTHLY") Period period){
        String username = authentication.getName();

        return expenseService.getBudgetStatus(username, period);
    }
}
