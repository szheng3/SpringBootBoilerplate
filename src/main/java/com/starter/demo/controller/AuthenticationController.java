package com.starter.demo.controller;

import com.google.gson.Gson;
import com.starter.demo.configuration.exception.runtime.UnAuthorizedException;
import com.starter.demo.configuration.security.JWTUtil;
import com.starter.demo.configuration.security.PBKDF2Encoder;
import com.starter.demo.request.AuthRequest;
import com.starter.demo.response.AuthResponse;
import com.starter.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@Slf4j
public class AuthenticationController {

    private final JWTUtil jwtUtil;

    private final PBKDF2Encoder passwordEncoder;

    private final UserService userRepository;

    private final Gson gson;

    public AuthenticationController(JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder, UserService userRepository, Gson gson) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.gson = gson;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<AuthResponse> login(@RequestBody AuthRequest ar) {

        return Mono
                .fromSupplier(() -> userRepository.findByUsername(ar.getUsername()))
                .subscribeOn(Schedulers.elastic())
                .switchIfEmpty(Mono.error(new UnAuthorizedException("No User Found")))
                .map((userDetails) -> {
                    if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                        String access_token = jwtUtil.generateToken(userDetails);
                        return AuthResponse.builder().access_token(access_token).build();
                    }
                    throw new UnAuthorizedException();
                }).doOnNext(authResponse -> {
                    log.info("request body: {} response body:{}", gson.toJson(ar), gson.toJson(authResponse));
                });
    }


}
