package org.example.service;


import org.example.dao.UserRepository;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public boolean signup(User user) {
        return userRepository.signupser(user);
    }

    public User login(User user) {
        return userRepository.login(user);
    }

    public User findUser(User user) {
        return userRepository.findUser(user);
    }
}
