package com.example.library2.util;

import org.keycloak.authorization.client.AuthzClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class AuthServiceUtil {
    public static String getLogOutUrlString(AuthzClient authzClient) {
        return authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/logout";
    }

    public static String getRefreshUrlString(AuthzClient authzClient) {
        return authzClient.getConfiguration().getAuthServerUrl() + "/realms/" + authzClient.getConfiguration().getRealm() + "/protocol/openid-connect/token";
    }

    public static MultiValueMap<String, String> getMap(AuthzClient authzClient, String refresh) {
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