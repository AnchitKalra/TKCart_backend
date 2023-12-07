package org.example.service;


import org.example.dao.ProfileRepository;
import org.example.model.User;
import org.example.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    public boolean saveProfile(UserProfile userProfile) {
       return profileRepository.saveProfile(userProfile);
    }

    public UserProfile getProfile(User user) {
        return profileRepository.getUserProfile(user);
    }
}
