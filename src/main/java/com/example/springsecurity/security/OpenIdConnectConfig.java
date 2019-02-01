package com.example.springsecurity.security;

import com.example.springsecurity.config.Client;
import com.example.springsecurity.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class OpenIdConnectConfig {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private Client client;

    @Bean
    public OAuth2ProtectedResourceDetails openIdConnect() {
        final AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(client.getClientId());
        details.setClientSecret(client.getClientSecret());
        details.setAccessTokenUri(jwtProvider.getToken_endpoint());
        details.setUserAuthorizationUri(jwtProvider.getAuthorization_endpoint());
        details.setScope(Arrays.asList("openid", "profile", "email"));
        details.setPreEstablishedRedirectUri(client.getRedirectUri());
        details.setUseCurrentUri(false);
        return details;
    }

    @Bean
    public OAuth2RestTemplate OpenIdRestTemplate(final OAuth2ClientContext clientContext) {
        final OAuth2RestTemplate template = new OAuth2RestTemplate(openIdConnect(), clientContext);
        return template;
    }

}
