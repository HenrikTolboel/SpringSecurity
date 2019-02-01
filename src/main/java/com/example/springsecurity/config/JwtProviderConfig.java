package com.example.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Configuration
public class JwtProviderConfig {

    @Value("${pingfed.issuer-uri}")
    private String issuerURI;

    public JwtProviderConfig() {
    }

    @Bean
    public JwtProvider jwtProvider() throws URISyntaxException, IOException {
        URI issuer = new URI(issuerURI);
        URL providerURL = issuer.resolve("/.well-known/openid-configuration").toURL();

        ObjectMapper mapper = new ObjectMapper();
        JwtProvider jwtProvider = mapper.readValue(providerURL, JwtProvider.class);
        return jwtProvider;

    }

}