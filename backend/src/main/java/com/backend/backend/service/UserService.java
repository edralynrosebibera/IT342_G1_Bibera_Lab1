package com.backend.backend.service;

import com.backend.backend.dto.AuthResponse;
import com.backend.backend.dto.LoginRequest;
import com.backend.backend.dto.RegisterRequest;
import com.backend.backend.model.User;
import com.backend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

        @Autowired
        private TokenProvider tokenProvider;

        @Autowired
        private ProfileService profileService;
    
    public AuthResponse register(RegisterRequest request) {
        AuthResponse response = new AuthResponse();
        
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Email is required");
            return response;
        }
        
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Password is required");
            return response;
        }
        
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            response.setSuccess(false);
            response.setMessage("Passwords do not match");
            return response;
        }
        
        if (request.getPassword().length() < 6) {
            response.setSuccess(false);
            response.setMessage("Password must be at least 6 characters");
            return response;
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            response.setSuccess(false);
            response.setMessage("Email already exists");
            return response;
        }
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        
        User savedUser = userRepository.save(user);
        
        response.setSuccess(true);
        response.setMessage("User registered successfully");
        response.setUserId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        
            
            profileService.createProfile(savedUser);
        
            
            String token = tokenProvider.generateToken(savedUser);
            response.setToken(token);
        
        return response;
    }
    
    public AuthResponse login(LoginRequest request) {
        AuthResponse response = new AuthResponse();
        
        
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Email is required");
            return response;
        }
        
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Password is required");
            return response;
        }
        
        
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        
        if (userOptional.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Invalid email or password");
            return response;
        }
        
        User user = userOptional.get();
        
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            response.setSuccess(false);
            response.setMessage("Invalid email or password");
            return response;
        }
        
        
        response.setSuccess(true);
        response.setMessage("Login successful");
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        
            
            String token = tokenProvider.generateToken(user);
            response.setToken(token);
        
        return response;
    }
}
