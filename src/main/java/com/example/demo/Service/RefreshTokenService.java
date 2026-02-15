package com.example.demo.Service;

import com.example.demo.Entity.RefreshToken;
import com.example.demo.Entity.User;
import com.example.demo.Repository.RefreshTokenRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repository;
    private final UserRepository userRepository;

    private final long REFRESH_EXPIRATION_DAYS = 7;

    public RefreshTokenService(RefreshTokenRepository repository,
                               UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public RefreshToken createRefreshToken(String username){
        User user = userRepository.findByUsername(username).orElseThrow();

        Optional<RefreshToken> existing = repository.findByUser(user);
        String token = UUID.randomUUID().toString();

        if(existing.isPresent()){
            RefreshToken refreshToken = existing.get();

            refreshToken.setToken(token);
            refreshToken.setExpiryDate(LocalDateTime.now().plusDays(REFRESH_EXPIRATION_DAYS));

            return repository.save(refreshToken);
        }

        RefreshToken refreshToken = new RefreshToken(token, user, LocalDateTime.now().plusDays(REFRESH_EXPIRATION_DAYS));

        return repository.save(refreshToken);

    }

    @Transactional
    public RefreshToken verifyToken(String token){
        RefreshToken refreshToken = repository.findByToken(token).orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if(refreshToken.isExpired()){
            repository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }
}
