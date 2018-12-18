package com.example.springSecurity.web;


import com.example.springSecurity.config.JwtProvider;
import com.example.springSecurity.security.OpenIdConnectUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class webController {

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/")
    public String home() {
        return "Greetings from Spring Boot! <p>Click </br><a href=\"/secure\">secure</a></br><a href=\"/index\">index</a></br><a href=\"/hello\">hello</a></br><a href=\"/logout\">logout</a></br>to see a greeting.</p>";
    }

    @GetMapping("/secure")
    public String secure(Principal principal) {
        //String name = principal.getName();

        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;

        OpenIdConnectUserDetails u = (OpenIdConnectUserDetails) user.getPrincipal();
        if (user.isAuthenticated() && user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return "secure page! - UserId=" + u.getUserId() + ", Username=" + u.getUsername();
        } else {
            return "secure page! Not granted access - UserId=" + u.getUserId() + ", Username=" + u.getUsername();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world page!";
    }

    @GetMapping("/index")
    public String index(Principal principal) {
        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;

        return "index page";
    }

    @GetMapping("/logout")
    public RedirectView logout(RedirectAttributes attributes, Principal principal) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "logout");

        if (null == principal) {
            return new RedirectView("/");
        }

        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;

        if (user.isAuthenticated()) {
            user.setAuthenticated(false);
            return new RedirectView(jwtProvider.getPing_end_session_endpoint());
        }

        return new RedirectView("/");
    }


    @GetMapping("/callback")
    public String callback() {
        return "callback page!";
    }


}

// https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#spring-security-oauth2-jose