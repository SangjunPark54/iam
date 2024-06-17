package com.example.iam.client;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClient {
    @Value("admin-cli")
    private String adminClientId;

    @Value("https://test-iam-admin.dev.cloudzcp.net")
    private String authServerUrl;

    @Value("master")
    private String realm;

    @Value("keycloak")
    private String username;

    @Value("Cloudzcp_2030!!")
    private String password;

    @Value("password")
    private String type;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(adminClientId)
                .grantType(type)
                .username(username)
                .password(password)
                .build();
    }
}
