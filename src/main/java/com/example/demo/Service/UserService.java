package com.example.demo.Service;

import com.example.demo.DTO.UserUpdate;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updateBudgetLimit(String username, Double limit) {

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        user.setBudgetLimit(limit);

        userRepository.save(user);
    }

    public Double getBudgetLimit(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        return user.getBudgetLimit();
    }

    public User updateUserDetails(String username, UserUpdate dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        user.setUsername(dto.getName());
        user.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return userRepository.save(user);
    }
}
