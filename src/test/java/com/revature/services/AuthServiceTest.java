package com.revature.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.dtos.LoginRequest;
import com.revature.models.User;

class AuthServiceTest {
    UserService userService = Mockito.mock(UserService.class);
    AuthService authService = new AuthService(userService);

    @Test
    void testFindByCredentialsSuccessful() {
        // arrange
        User user = new User(1, "r123@gmail.com", "p123", "Rob", "Banks");
        LoginRequest loginRequest = new LoginRequest("r123@gmail.com", "p123");
        Mockito.when(userService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(Optional.of(user));
        // act & assert
        Assertions.assertEquals(Optional.of(user), authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @Test
    void testFindByCredentialsUnsuccessful() {
        // arrange
        LoginRequest loginRequest = new LoginRequest("r123@gmail.com", "p123");
        Mockito.when(userService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(Optional.empty());
        // act & assert
        Assertions.assertEquals(Optional.empty(), authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @Test
    void testRegisterExistingUser(){
        // arrange
        User userExists = new User(5,"existingUser@mail.com","password","Yo","yo");
        Mockito.when(userService.getUserByEmail(userExists.getEmail())).thenReturn(Optional.of(userExists));
        // act & assert
        Assertions.assertEquals(null, authService.register(userExists));
    }

    @Test
    void testRegisterNewUser(){
        // arrange
        User userNotExists = new User(5,"existingUser@mail.com","password","Yo","yo");
        Mockito.when(userService.getUserByEmail(userNotExists.getEmail())).thenReturn(Optional.empty());
        User actual = userService.save(userNotExists);
        // act & assert
        Assertions.assertEquals(actual, authService.register(userNotExists));
    }



}
