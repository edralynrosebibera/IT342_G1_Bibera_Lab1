package com.backend.backend.service;

import com.backend.backend.model.Profile;
import com.backend.backend.model.User;
import com.backend.backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    
    @Autowired
    private ProfileRepository profileRepository;
    
    public Profile getProfile(int userId) {
        // In a real implementation, you would fetch by User ID
        Optional<Profile> profile = profileRepository.findById(userId);
        return profile.orElse(null);
    }
    
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }
    
    public Profile getProfileByUser(User user) {
        Optional<Profile> profile = profileRepository.findByUser(user);
        return profile.orElse(null);
    }
    
    public Profile createProfile(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());
        return profileRepository.save(profile);
    }
}
