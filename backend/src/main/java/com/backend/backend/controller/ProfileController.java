package com.backend.backend.controller;

import com.backend.backend.model.Profile;
import com.backend.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<Profile> viewProfile(@PathVariable int userId) {
        Profile profile = profileService.getProfile(userId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int userId, @RequestBody Profile profile) {
        profile.setProfileId(userId);
        Profile updatedProfile = profileService.updateProfile(profile);
        return ResponseEntity.ok(updatedProfile);
    }
}
