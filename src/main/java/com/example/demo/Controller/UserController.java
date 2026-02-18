package com.example.demo.Controller;

import com.example.demo.DTO.BudgetLimit;
import com.example.demo.DTO.UserUpdate;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication){
        String username = authentication.getName();

        return userService.getCurrentUser(username);
    }

    @PutMapping("/budget")
    public ResponseEntity<Void> updateBudget(@RequestBody BudgetLimit dto, Authentication authentication){
        String username = authentication.getName();

        userService.updateBudgetLimit(username, dto.getBudgetLimit());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/budget")
    public ResponseEntity<Double> getBudget(Authentication authentication){

        String username = authentication.getName();

        return ResponseEntity.ok(
                userService.getBudgetLimit(username)
        );
    }

    @PutMapping("/update-details")
    public ResponseEntity<User> updateProfile(@RequestBody UserUpdate dto, Authentication authentication){
        String username = authentication.getName();

        User updatedUser = userService.updateUserDetails(username, dto);

        return ResponseEntity.ok(updatedUser);
    }

}
