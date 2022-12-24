package com.example.library2.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
    public HttpHeaders getHttpHeaders() {
        return new HttpHeaders();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
