package org.lucky.springinterview.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GreetingApp {

    public static void main(String[] args) {
        SpringApplication.run(GreetingApp.class, args);
    }

    @Bean
    public WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }
}
