package org.lucky.springinterview;

import org.junit.jupiter.api.Test;
import org.lucky.springinterview.controller.GreetingController;
import org.springframework.test.web.reactive.server.WebTestClient;

public class GreetingControllerTest {

    @Test
    public void test_say_hello() throws Exception{
        WebTestClient
                .bindToController(GreetingController.class)
                .build()
                .get()
                .uri("/hello/2")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(GreetingController.Greeting.class)
                .isEqualTo(new GreetingController.Greeting("Sorry User...!"));
//        System.out.println(message);
    }
}
