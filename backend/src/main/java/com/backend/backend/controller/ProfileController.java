package com.backend.backend.controller;

import com.backend.backend.model.Profile;
import com.backend.backend.model.User;
import com.backend.backend.service.ProfileService;
import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Profile> viewProfile(@PathVariable Long userId) {

        User user = userService.findById(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Profile profile = profileService.getProfileByUser(user);

        if (profile != null) {
            return ResponseEntity.ok(profile);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Long userId,
            @RequestBody Profile profile) {

        User user = userService.findById(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Profile updated =
                profileService.updateProfile(profile, user);

        return ResponseEntity.ok(updated);
    }
}
