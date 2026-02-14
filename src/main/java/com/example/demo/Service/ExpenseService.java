package com.example.demo.Service;

import com.example.demo.DTO.BudgetStatus;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Expense;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ExpenseRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository) {

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Expense createExpense(String username, Long categoryId, Double amount, String description) {

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow();

        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setDate(LocalDate.now());
        expense.setUser(user);
        expense.setDescription(description);
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpenses(String username) {
        return expenseRepository.findByUserUsernameOrderByDateDesc(username);
    }

    public BudgetStatus getBudgetStatus(String username, String period){
        LocalDate now = LocalDate.now();

        LocalDate start;

        switch (period.toUpperCase()){
            case "DAILY":
                start = now;
                break;
            case "WEEKLY":
                start = now.minusDays(7);
            case "MONTHLY":
                start = now.minusMonths(1);
                break;
            default:
                throw new RuntimeException("Invalid period");

        }

        LocalDate end = now;

        Double totalSpent = expenseRepository.getTotalSpentForPeriod(username, start, end);

        if (totalSpent == null)
            totalSpent = 0.0;

        List<Object[]> results = expenseRepository.getCategoryTotalsByPeriod(username, start, end);

        Map<String, Double> categoryTotals = new HashMap<>();

        for(Object[] row : results){
            String categoryName = (String) row[0];
            Double amount = (Double) row[1];

            categoryTotals.put(categoryName, amount);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Double limit = user.getBudgetLimit();

        Double remaining = limit - totalSpent;
        Double percantage = limit > 0 ? (totalSpent / limit) * 100 : 0;

        BudgetStatus budgetStatus = new BudgetStatus();

        budgetStatus.setTotalLimit(limit);
        budgetStatus.setSpentAmount(totalSpent);
        budgetStatus.setRemainingAmount(remaining);
        budgetStatus.setSpentPercentage(percantage);
        budgetStatus.setCategoryTotals(categoryTotals);

        return budgetStatus;
    }
}
