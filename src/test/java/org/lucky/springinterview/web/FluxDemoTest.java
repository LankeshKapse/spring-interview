package org.lucky.springinterview.web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class FluxDemoTest {

    @Test
    public void demoTest() throws Exception{


        RouterFunction<ServerResponse> route = RouterFunctions.route(
                RequestPredicates.GET("/users"),
                request -> ServerResponse.accepted().bodyValue("those are all users...!")
        );

        WebTestClient
                .bindToRouterFunction(route)
                .build()
                .get()
                .uri("/users")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody().consumeWith(data -> {
                    System.out.println(new String(data.getResponseBody()));
                });

//        System.out.println(client);
    }
}
