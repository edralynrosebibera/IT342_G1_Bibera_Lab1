package com.backend.backend.controller;

import com.backend.backend.dto.AuthResponse;
import com.backend.backend.dto.LoginRequest;
import com.backend.backend.dto.RegisterRequest;
import com.backend.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class AuthController {
    
    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = userService.register(request);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = userService.login(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(@RequestHeader("Authorization") String token) {
        AuthResponse response = new AuthResponse();
        response.setSuccess(true);
        response.setMessage("Logged out successfully");
        return ResponseEntity.ok(response);
    }
}
