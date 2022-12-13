package com.example.library2.service;

import com.example.library2.config.LoginResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.util.Http;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private final AuthzClient authzClient;

    public LoginResponseMessage login(String email, String pass) {
        log.info("START login for user {}", email);
        val response = authzClient.authorization("user2", "123").authorize();
        val result = new LoginResponseMessage()
                .builder().
                tokenType(response.getTokenType()).
                refreshToken(response.getRefreshToken()).
                token(response.getToken()).build();
        log.info("FINISH login for user {} successfully", email);
        return result;
    }

    public LoginResponseMessage tokenRefresh(String refresh) {
        log.info("START tokenRefresh");
        String url = authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/token";
        String clientId = authzClient.getConfiguration().getResource();
        String secret = (String) authzClient.getConfiguration().getCredentials().get("secret");
        val http = new Http(authzClient.getConfiguration(), (params, headers) -> {
        });

        val response = http.<AccessTokenResponse>post(url)
                .authentication()
                .client()
                .form()
                .param("grant_type", "refresh_token")
                .param("refresh_token", refresh)
                .param("client_id", clientId)
                .param("client_secret", secret)
                .response()
                .json(AccessTokenResponse.class)
                .execute();

        val result = new LoginResponseMessage()
                .builder().
                tokenType(response.getTokenType()).
                refreshToken(response.getRefreshToken()).
                token(response.getToken()).build();
        log.info("FINISH tokenRefresh");
        return result;
    }

    public void logout(String token) {

    }

}