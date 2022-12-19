package com.example.library2.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration

public class KeycloakConfiguration {
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public AuthzClient getAuthzClient() {
        org.keycloak.authorization.client.Configuration configuration = new org.keycloak.authorization.client.Configuration(
                "http://localhost:8182",
                "lib-realm",
                "lib-client",
                Collections.singletonMap("secret", "okaePTxAf9UwxJeCWjIqbqTjAcsw2tsy"),
                null);
        return AuthzClient.create(configuration);
    }

    @Bean
    @Primary
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public HttpHeaders getHttpHeaders(){
        return new HttpHeaders();
    }
}
