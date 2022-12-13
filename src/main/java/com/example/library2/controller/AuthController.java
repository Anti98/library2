package com.example.library2.controller;

import com.example.library2.config.LoginResponseMessage;
import com.example.library2.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
@AllArgsConstructor
public class AuthController {
    AuthzClient authzClient;
    private AuthService authService;

    @PostMapping("/tokenRefresh")
    @PreAuthorize("permitAll()")
    public LoginResponseMessage tokenRefresh(String refreshToken) {
        val responseMessage = authService.tokenRefresh(refreshToken);
        return responseMessage;
    }


    @PostMapping()
    @PreAuthorize("permitAll()")
    public LoginResponseMessage login(String email, String pass) {
        val responseMessage = authService.login(email, pass);
        return responseMessage;
    }
}

