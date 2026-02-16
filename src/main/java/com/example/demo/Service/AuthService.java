package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.JWT.JWTService;
import com.example.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(String username, String password){
        if(userRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return jwtService.generateToken(user.getUsername());
    }


    public String login(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return jwtService.generateToken(user.getUsername());
    }

    public void updateBudgetLimit(String username, Double limit){
        User user = userRepository.findByUsername(username).orElseThrow();

        user.setBudgetLimit(limit);

        userRepository.save(user);
    }

    public Double getBudgetLimit(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        return user.getBudgetLimit();
    }

}
