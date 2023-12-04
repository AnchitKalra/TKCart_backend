package org.example.service;


import org.example.dao.ProfileRepository;
import org.example.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    public boolean saveProfile(UserProfile userProfile) {
       return profileRepository.saveProfile(userProfile);
    }
}
