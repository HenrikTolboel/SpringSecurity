package com.example.springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@Configuration
public class JwtDecoderConfig {

    @Autowired
    private JwtProvider jwtProvider;

    public JwtDecoderConfig() {
    }

    @Bean
    JwtDecoder jwtDecoder() {
        JwtDecoder decoder = JwtDecoders.fromOidcIssuerLocation(jwtProvider.getIssuer());
        return decoder;
    }

}