package org.example.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/signup")
    @ResponseBody


    public boolean signup( @RequestBody(required = true) User user ) {
        if(userService.signup(user))
            return true;
        else {
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/login")
    @ResponseBody
    public User login(@RequestBody(required = true) User user) {
       return userService.login(user);
    }
}
