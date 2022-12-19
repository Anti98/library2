package com.example.library2.service;

import com.example.library2.exception.BadStatusCode;
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
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    public AccessTokenResponse login(UserCredentials userCredentials) {
        return authzClient.obtainAccessToken(userCredentials.getUserName(), userCredentials.getPassword());
    }

    public AccessTokenResponse tokenRefresh(String refresh) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(AuthServiceUtil.getMap(authzClient, refresh), httpHeaders);
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(AuthServiceUtil.getRefreshUrlString(authzClient), request, AccessTokenResponse.class);
        if (response.getStatusCode().is2xxSuccessful())
            if (response.getBody() != null) return response.getBody();
            else return new AccessTokenResponse();
        throw new BadStatusCode("SMTH go wrong");
    }

    public String logout(String refreshToken) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(AuthServiceUtil.getMap(authzClient, refreshToken), httpHeaders);
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(AuthServiceUtil.getLogOutUrlString(authzClient), request, AccessTokenResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) return "Logout complete";
        throw new BadStatusCode("SMTH go wrong");
    }

    private static class AuthServiceUtil {
        private static String getLogOutUrlString(AuthzClient authzClient) {
            return authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/logout";
        }

        private static String getRefreshUrlString(AuthzClient authzClient) {
            return authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/token";
        }

        private static MultiValueMap<String, String> getMap(AuthzClient authzClient, String refresh) {
            String clientId = authzClient.getConfiguration().getResource();
            String secret = String.valueOf(authzClient.getConfiguration().getCredentials().get("secret"));
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "refresh_token");
            map.add("refresh_token", refresh);
            map.add("client_id", clientId);
            map.add("client_secret", secret);
            return map;
        }
    }
}
