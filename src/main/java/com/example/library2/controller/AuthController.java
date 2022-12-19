package com.example.library2.controller;

import com.example.library2.model.dto.security.UserCredentials;
import com.example.library2.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
@Tag(name = "Учетные данные", description = "API для работы с токенами авторизации")
public class AuthController {
    private AuthService authService;

    @Operation(summary = "Обновление токена", description = "Обновление токенов через refresh_token")
    @PostMapping("/tokenRefresh")
    public AccessTokenResponse tokenRefresh(@RequestBody String refreshToken) {
        return authService.tokenRefresh(refreshToken);
    }

    @Operation(summary = "Логин", description = "Получение токенов авторизации по логину и паролю")
    @PostMapping
    public AccessTokenResponse login(@RequestBody UserCredentials userCredentials) {
        return authService.login(userCredentials);
    }

    @Operation(summary = "Завершение сессии", description = "Завершение сессии по refresh токену")
    @PostMapping("/logout")
    public String logOut(@RequestBody String refreshToken) {
        return authService.logout(refreshToken);
    }
}

