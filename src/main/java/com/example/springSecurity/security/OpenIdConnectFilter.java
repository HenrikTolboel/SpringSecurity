package com.example.springSecurity.security;

import com.example.springSecurity.config.Client;
import com.example.springSecurity.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class OpenIdConnectFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    JwtDecoder jwtDecoder;

    @Autowired
    Client client;

    public OAuth2RestOperations restTemplate;

    public OpenIdConnectFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(new NoopAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

/**
 *       Performs actual authentication.
 *
 *       The implementation should do one of the following:
 *
 *       1. Return a populated authentication token for the authenticated user, indicating successful authentication
 *       2. Return null, indicating that the authentication process is still in progress. Before returning, the
 *          implementation should perform any additional work required to complete the process.
 *       3. Throw an AuthenticationException if the authentication process fails
 *
 *        Parameters:
 *        request  - from which to extract parameters and perform the authentication
 *        response - the response, which may be needed if the implementation has to do a redirect as part of a
 *                   multi-stage authentication process (such as OpenID).
 *
 *        Returns:
 *        the authenticated user token, or null if authentication is incomplete.
 */


        OAuth2AccessToken accessToken;
        try {
            accessToken = restTemplate.getAccessToken();
        } catch (final OAuth2Exception e) {
            throw new BadCredentialsException("Could not obtain access token", e);
        }

        final String idToken = accessToken.getAdditionalInformation().get("id_token").toString();

        Jwt jwt = jwtDecoder.decode(idToken);

        //verify(jwt);

        final OpenIdConnectUserDetails user = new OpenIdConnectUserDetails(jwt.getClaims(), accessToken);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        return usernamePasswordAuthenticationToken;
    }

    private void verify(Jwt jwt) throws AuthenticationException {
        int exp = (int) jwt.getClaims().get("exp");
        Date expireDate = new Date(exp * 1000L);
        Date now = new Date();
        if (expireDate.before(now) ||
                !jwt.getClaims().get("iss").equals(jwtProvider.getIssuer()) ||
                !jwt.getClaims().get("aud").equals(client.getClientId())) {
            throw new RuntimeException("Invalid claims");
        }
    }



    public void setRestTemplate(OAuth2RestTemplate restTemplate2) {
        restTemplate = restTemplate2;
    }

    private static class NoopAuthenticationManager implements AuthenticationManager {

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            throw new UnsupportedOperationException("No authentication should be done with this AuthenticationManager");
        }

    }
}
