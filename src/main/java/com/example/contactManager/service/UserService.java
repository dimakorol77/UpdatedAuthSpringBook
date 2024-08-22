package com.example.contactManager.service;

import com.example.contactManager.dto.LoginRequestDto;
import com.example.contactManager.dto.LoginResponseDto;
import com.example.contactManager.dto.RegisterRequestDto;
import com.example.contactManager.model.User;
import com.example.contactManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(RegisterRequestDto request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Пароль должен быть захеширован
        user.setName(request.getName());
        return userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(request.getPassword())) {
            String token = generateToken(userOptional.get());
            return new LoginResponseDto(request.getEmail(), token);
        }
        throw new RuntimeException("Invalid credentials");
    }

    private String generateToken(User user) {
        return "001{" + user.getEmail() + "|" + user.getPassword() + "}";
    }

    public Optional<User> findByToken(String token) {
        if (token.startsWith("001{") && token.endsWith("}")) {
            String[] parts = token.substring(4, token.length() - 1).split("\\|");
            if (parts.length == 2) {
                return userRepository.findByEmail(parts[0]);
            }
        }
        return Optional.empty();
    }
}
