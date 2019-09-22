package com.starter.demo.controller;


import com.starter.demo.response.IResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;


@RestController
public class UserController {

    @PostMapping("/")
    public Mono<IResponse> getMapping(ServerHttpRequest serverHttpRequest){
        serverHttpRequest.getBody().map(dataBuffer -> this.convert(dataBuffer.asInputStream(),StandardCharsets.UTF_8)).subscribe(System.out::println);
        System.out.println(serverHttpRequest.getHeaders().get("test"));

        return Mono.just(new IResponse());
    }
    public String convert(InputStream inputStream, Charset charset)  {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
