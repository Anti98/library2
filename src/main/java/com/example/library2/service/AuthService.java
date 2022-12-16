package com.example.library2.service;

import com.example.library2.model.dto.security.UserCredentials;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private final AuthzClient authzClient;

    public AccessTokenResponse login(UserCredentials userCredentials) {
        return authzClient.obtainAccessToken(userCredentials.getUserName(), userCredentials.getPassword());
    }

    public AccessTokenResponse tokenRefresh(String refresh) {
        String url = authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/token";
        String clientId = authzClient.getConfiguration().getResource();
        String secret = (String) authzClient.getConfiguration().getCredentials().get("secret");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refresh);
        map.add("client_id", clientId);
        map.add("client_secret", secret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(url, request, AccessTokenResponse.class);
        return response.getBody();
    }

    public void logout(String refreshToken) {
        String url = authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/logout";
        String clientId = authzClient.getConfiguration().getResource();
        String secret = (String) authzClient.getConfiguration().getCredentials().get("secret");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("refresh_token", refreshToken);
        map.add("client_id", clientId);
        map.add("client_secret", secret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(url, request, AccessTokenResponse.class);
    }
}