package com.example.library2.controller;

import com.example.library2.model.security.LoginResponseMessage;
import com.example.library2.model.security.UserCredentials;
import com.example.library2.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/tokenRefresh")
    public LoginResponseMessage tokenRefresh(@RequestBody String refreshToken) {

        return authService.tokenRefresh(refreshToken);
    }

    @PostMapping
    public LoginResponseMessage login(@RequestBody UserCredentials userCredentials) {
        return authService.login(userCredentials);
    }

    @PostMapping("/logout")
    public String logOut(@RequestBody String refreshToken) {
        authService.logout(refreshToken);
        return "Logout complete";
    }
}

