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

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public AuthResponse register(RegisterRequest request) {

        AuthResponse response = new AuthResponse();

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

        profileService.createProfile(savedUser);

        String token = tokenProvider.generateToken(savedUser);

        response.setSuccess(true);
        response.setMessage("User registered successfully");
        response.setUserId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setToken(token);

        return response;
    }

    public AuthResponse login(LoginRequest request) {

        AuthResponse response = new AuthResponse();

        Optional<User> userOptional =
                userRepository.findByEmail(request.getEmail());

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

        String token = tokenProvider.generateToken(user);

        response.setSuccess(true);
        response.setMessage("Login successful");
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setToken(token);

        return response;
    }
}
