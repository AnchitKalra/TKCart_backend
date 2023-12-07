package org.example.controller;

import org.example.model.User;
import org.example.model.UserProfile;
import org.example.service.ProfileService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    ProfileService profileService;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/profile/saveImage")
    @ResponseBody
    public ResponseEntity<UserProfile> saveProfile(@RequestBody(required = true) UserProfile userProfile) {
        try {
            System.out.println(userProfile.getProfileId());
            System.out.println(userProfile.getUser());
            System.out.println(userProfile.getImage());
            User user = userService.findUser(userProfile.getUser());
            userProfile.setUser(user);
            profileService.saveProfile(userProfile);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/profile/getProfile")
    @ResponseBody
    public ResponseEntity<UserProfile> getProfile(@RequestBody(required = true) User user) {
        try {
            User user1 = userService.findUser(user);
          UserProfile userProfile =  profileService.getProfile(user1);
          return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR HERE");
        }
        return null;
    }


}
