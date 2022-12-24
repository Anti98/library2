package com.example.library2.service;

import com.example.library2.exception.BadStatusCode;
import com.example.library2.model.dto.security.UserCredentials;
import com.example.library2.util.AuthServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AuthService {
    private final AuthzClient authzClient;
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    @Autowired
    public AuthService(AuthzClient authzClient, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        httpHeaders = new HttpHeaders();
        this.authzClient = authzClient;
    }

    public AccessTokenResponse login(UserCredentials userCredentials) {
        return authzClient.obtainAccessToken(userCredentials.getUserName(), userCredentials.getPassword());
    }

    public AccessTokenResponse tokenRefresh(String refresh) {
        MultiValueMap<String, String> headerMap = AuthServiceUtil.getMap(authzClient, refresh);
        String refreshTokenUrl = AuthServiceUtil.getRefreshUrlString(authzClient);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headerMap, httpHeaders);
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(refreshTokenUrl, request, AccessTokenResponse.class);
        if (response.getStatusCode().is2xxSuccessful())
            if (response.getBody() != null) return response.getBody();
            else return new AccessTokenResponse();
        throw new BadStatusCode("SMTH go wrong");
    }

    public String logout(String refreshToken) {
        String logOutUrl = AuthServiceUtil.getLogOutUrlString(authzClient);
        MultiValueMap<String, String> headerMap = AuthServiceUtil.getMap(authzClient, refreshToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headerMap, httpHeaders);
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(logOutUrl, request, AccessTokenResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) return "Logout complete";
        throw new BadStatusCode("SMTH go wrong");
    }
}
