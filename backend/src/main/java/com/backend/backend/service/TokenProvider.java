package com.backend.backend.service;

import com.backend.backend.model.User;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenProvider {
    
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5 hours
    private static final String SECRET_KEY = "your-secret-key-change-in-production";
    
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        return createToken(claims, user.getEmail());
    }
    
    private String createToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiresAt = new Date(now + JWT_TOKEN_VALIDITY);
        
        return subject + ":" + System.currentTimeMillis() + ":" + expiresAt.getTime();
    }
    
    public boolean validateToken(String token) {
        try {
            String[] parts = token.split(":");
            if (parts.length != 3) {
                return false;
            }
            long expiresAt = Long.parseLong(parts[2]);
            return System.currentTimeMillis() < expiresAt;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void invalidateToken(String token) {
        
    }
}
