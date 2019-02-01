package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;

public class ClientConfig {

    public ClientConfig() {
    }

    @Bean
   Client client() {
       return new Client();
   }

}
