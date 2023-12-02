package org.example.service;


import org.example.dao.UserRepository;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class
UserService {

    @Autowired
    UserRepository userRepository;
    public User signup(User user) {
        return userRepository.signupser(user);
    }

    public User login(User user) {

        return userRepository.login(user);
    }

    public User loginWithToken(String token) {
       return userRepository.loginWithToken(token);
    }

    public User findUser(User user) {
        return userRepository.findUser(user);
    }

    public boolean persistUser(User user) {
       return userRepository.saveUserWithToken(user);
    }

}
