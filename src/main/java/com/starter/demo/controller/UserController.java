package com.starter.demo.controller;

import com.starter.demo.response.IResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @GetMapping("/")
    public Mono<IResponse> getMapping(){

        return Mono.just(new IResponse());
    }
}
