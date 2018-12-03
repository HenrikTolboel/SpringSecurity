package com.example.springSecurity.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class webController {

    @RequestMapping("/")
    public String home() {
        return "Greetings from Spring Boot! <p>Click <a href=\"/about\">here</a> to see a greeting.</p>";
    }

    @RequestMapping("/about")
    public String about(Principal principal) {
        String name = principal.getName();
        return "about page!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world page!";
    }

    @RequestMapping("/callback")
    public String callback() {
        return "callback page!";
    }
}