package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateBudgetLimit(String username, Double limit){

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        user.setBudgetLimit(limit);

        userRepository.save(user);
    }

    public Double getBudgetLimit(String username){

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        return user.getBudgetLimit();
    }
}
