package com.revature.services;

import com.revature.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public User register(User user) {
        Optional<User> useropt = userService.getUserByEmail(user.getEmail());
        if(useropt.isPresent()){
            return null;
        }
        return userService.save(user);
    }

 


     
}
