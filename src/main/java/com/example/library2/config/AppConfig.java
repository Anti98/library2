package com.example.library2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig {
    @Bean
    @Primary
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
