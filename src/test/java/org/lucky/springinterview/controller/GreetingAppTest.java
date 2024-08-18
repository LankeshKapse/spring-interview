package org.lucky.springinterview.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GreetingAppTest {

    @Autowired
    WebClient client;
    @Test
    public void testHello() throws Exception{
        Assertions.assertThrows(ErrorResponse.class, () -> {
            client.get()
                    .uri("/hello/2")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class).map(ErrorResponse::new))
                    .bodyToMono(String.class)
                    .block();
        });

        String fromError = client.get()
                .uri("/hello/2")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("From Error")
                .map(String::toUpperCase)
                .block();
        Assertions.assertEquals(fromError,"FROM ERROR");

        String onResume = client.get()
                .uri("/hello/2")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("On Resume"))
                .block();

        Assertions.assertEquals(onResume,"On Resume");

        client.get()
                .uri("/hello/2")
                .retrieve()
                .bodyToMono(String.class);

    }
}
@ToString
@Getter
@Setter
class ErrorResponse extends RuntimeException{
    private String message;
    public ErrorResponse(String message){
        this.message = message;
    }
}