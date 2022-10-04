package com.revature.controllers;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.services.AuthService;

class AuthControllerTest {

    HttpSession session = Mockito.mock(HttpSession.class);
    AuthService authService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(authService);

    @Test
    void testLoginSuccessful() {
        // arrange
        User user = new User(1, "r123@gmail.com", "p123", "Rob", "Banks");
        LoginRequest loginRequest = new LoginRequest("r123@gmail.com", "p123");
        Mockito.when(authService.findByCredentials(anyString(), anyString())).thenReturn(Optional.of(user));
        // act & assert
        Assertions.assertEquals(ResponseEntity.ok(user), authController.login(loginRequest, session));
        Mockito.verify(session, Mockito.times(1)).setAttribute("user", user);
    }

    @Test
    void testLoginUnsuccessful(){
        // arrange
        LoginRequest loginRequest = new LoginRequest("r123@gmail.com", "p123");
        Mockito.when(authService.findByCredentials(anyString(), anyString())).thenReturn(Optional.empty());
        // act & assert
        Assertions.assertEquals(authController.login(loginRequest, session), ResponseEntity.badRequest().build());
    }

    @Test
    void testLogout() {
        // act & assert
        Assertions.assertEquals(authController.logout(session), ResponseEntity.ok().build());
        Mockito.verify(session, Mockito.times(1)).removeAttribute("user");
    }

    @Test
    void testRegisterSuccessful() {
        // arrange
        RegisterRequest registerRequest = new RegisterRequest("r123@gmail.com", "p123", "Rob", "Banks");
        User user = new User(0, registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getFirstName(), registerRequest.getLastName());
        Mockito.when(authService.register(new User(0, registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getFirstName(), registerRequest.getLastName()))).thenReturn(user);
        // act & assert
        Assertions.assertEquals(authController.register(registerRequest), ResponseEntity.status(HttpStatus.CREATED).body(authService.register(user)));
    }

    @Test
    void testRegisterFail() {
        // arrange
        RegisterRequest registerRequest = new RegisterRequest("r123@gmail.com", "z123", "Rob", "Banks");
        Mockito.when(authService.register(new User(0, registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getFirstName(), registerRequest.getLastName()))).thenReturn(null);
        // act & assert
        Assertions.assertEquals(authController.register(registerRequest),  ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
