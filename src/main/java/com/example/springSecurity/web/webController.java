package com.example.springSecurity.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class webController {

    @RequestMapping("/")
    public String home() {
        return "Greetings from Spring Boot! <p>Click </br><a href=\"/about\">about</a></br><a href=\"/test\">test</a></br><a href=\"/hello\">hello</a></br>to see a greeting.</p>";
    }

    @RequestMapping("/about")
    public String about(Principal principal) {
        //String name = principal.getName();

        return "about page!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world page!";
    }

    @RequestMapping("/index")
    public String index() {
        return "index page!";
    }

    @RequestMapping("/callback")
    public String callback() {
        return "callback page!";
    }


    @GetMapping("/test")
    public String test(Principal principal) {
        return String.format("Hello, %s!", principal.toString());
    }


}

// https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#spring-security-oauth2-jose