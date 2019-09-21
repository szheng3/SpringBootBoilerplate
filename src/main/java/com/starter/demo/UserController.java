package com.starter.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @GetMapping("/")
    public Mono<String> getMapping(){

        return Mono.just("Up");
    }
}
