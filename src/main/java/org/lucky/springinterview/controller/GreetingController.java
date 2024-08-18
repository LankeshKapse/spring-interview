package org.lucky.springinterview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {

    public record Greeting(String message) {}

    AtomicInteger   counter  = new AtomicInteger(0);


    @GetMapping("/hello/{id}")
    public ResponseEntity<?> sayHello(@PathVariable("id") int val){
        if(val==1)
            return ResponseEntity.ok(new Greeting("Hello User "+counter.incrementAndGet()));
        else
            return ResponseEntity.badRequest().body(new Greeting("Sorry User "+counter.incrementAndGet()));
    }
}
