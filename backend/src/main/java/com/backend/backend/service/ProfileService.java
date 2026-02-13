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

    public Profile getProfileByUser(User user) {
        return profileRepository.findByUser(user).orElse(null);
    }

    public Profile updateProfile(Profile updatedProfile, User user) {

        Optional<Profile> existing =
                profileRepository.findByUser(user);

        Profile profile;

        if (existing.isPresent()) {

            profile = existing.get();

            profile.setFirstName(updatedProfile.getFirstName());
            profile.setLastName(updatedProfile.getLastName());
            profile.setBio(updatedProfile.getBio());
            profile.setPhoneNumber(updatedProfile.getPhoneNumber());

        } else {

            profile = new Profile();
            profile.setUser(user);
            profile.setFirstName(updatedProfile.getFirstName());
            profile.setLastName(updatedProfile.getLastName());
            profile.setBio(updatedProfile.getBio());
            profile.setPhoneNumber(updatedProfile.getPhoneNumber());
        }

        return profileRepository.save(profile);
    }

    public Profile createProfile(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());
        return profileRepository.save(profile);
    }
}
