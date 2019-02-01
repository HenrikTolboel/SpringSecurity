package com.example.springsecurity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Client {
    @Value("${pingfed.client-id}")
    private String clientId;

    @Value("${pingfed.client-secret}")
    private String clientSecret;

    @Value("${pingfed.redirectUri}")
    private String redirectUri;

    public Client() {
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
