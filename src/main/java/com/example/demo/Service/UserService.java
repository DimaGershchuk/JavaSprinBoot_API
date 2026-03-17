package com.example.demo.Service;

import com.example.demo.DTO.AuthResponse;
import com.example.demo.DTO.UserUpdate;
import com.example.demo.Entity.User;
import com.example.demo.JWT.JWTService;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService; // Використовуємо твій клас тут

    // Оновлений конструктор для ін'єкції саме твого сервісу
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User getCurrentUser(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
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

    public AuthResponse updateUserDetails(String username, UserUpdate dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            user.setUsername(dto.getName());
        }

        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User savedUser = userRepository.save(user);

        String newToken = jwtService.generateToken(savedUser.getUsername());

        return new AuthResponse(newToken, savedUser.getUsername());
    }
}
