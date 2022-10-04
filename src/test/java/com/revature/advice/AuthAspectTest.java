package com.revature.advice;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.revature.models.User;

class AuthAspectTest {
    
    HttpSession session = Mockito.mock(HttpSession.class);

    @Test
    void testAuthenticate(){
        // arrange
        User user = new User(1, "j@gmail.com", "password", "firstName", "lastName");
        Mockito.when(session.getAttribute("user")).thenReturn(session);
        // act
        session.setAttribute("user", user);
        // assert
        Assertions.assertNotNull(session.getAttribute("user"));
    }

    @Test
    void testAuthenticateFail(){
        // arrange
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        // act
        session.setAttribute("user", null);
        // assert
        Assertions.assertNull(session.getAttribute("user"));
    }
}
