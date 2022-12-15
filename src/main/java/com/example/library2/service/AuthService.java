package com.example.library2.service;

import com.example.library2.model.security.LoginResponseMessage;
import com.example.library2.model.security.UserCredentials;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.util.Http;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private final AuthzClient authzClient;

    public LoginResponseMessage login(UserCredentials userCredentials) {
        AuthorizationResponse response = authzClient.authorization(userCredentials.getUserName(), userCredentials.getPassword()).authorize();
        return LoginResponseMessage
                .builder().
                tokenType(response.getTokenType()).
                refreshToken(response.getRefreshToken()).
                token(response.getToken()).build();
    }

    public LoginResponseMessage tokenRefresh(String refresh) {
        String url = authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/token";
        String clientId = authzClient.getConfiguration().getResource();
        String secret = (String) authzClient.getConfiguration().getCredentials().get("secret");
        Http http = new Http(authzClient.getConfiguration(), (params, headers) -> {
        });

        AccessTokenResponse response = http.<AccessTokenResponse>post(url)
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

        return LoginResponseMessage
                .builder().
                tokenType(response.getTokenType()).
                refreshToken(response.getRefreshToken()).
                token(response.getToken()).build();
    }

    public void logout(String refreshToken) {
        String url = authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/logout";
        String clientId = authzClient.getConfiguration().getResource();
        String secret = (String) authzClient.getConfiguration().getCredentials().get("secret");
        Http http = new Http(authzClient.getConfiguration(), (params, headers) -> {
        });

        http.<Object>post(url)
                .authentication()
                .client()
                .form()
                .param("refresh_token", refreshToken)
                .param("client_id", clientId)
                .param("client_secret", secret)
                .response()
                .json(Object.class)
                .execute();
    }
}