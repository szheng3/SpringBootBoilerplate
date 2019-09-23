package com.starter.demo.controller;

import com.starter.demo.configuration.exception.runtime.UnAuthorizedException;
import com.starter.demo.configuration.security.JWTUtil;
import com.starter.demo.configuration.security.PBKDF2Encoder;
import com.starter.demo.request.AuthRequest;
import com.starter.demo.response.AuthResponse;
import com.starter.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private UserService userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<AuthResponse> login(@RequestBody AuthRequest ar) {
        return userRepository
                .findByUsername(ar.getUsername())
                .switchIfEmpty(Mono.error(new UnAuthorizedException("No User Found")))
                .map((userDetails) -> {
                    if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                        String access_token = jwtUtil.generateToken(userDetails);
                        return AuthResponse.builder().access_token(access_token).build();
                    }
                    throw new UnAuthorizedException();
                });
    }


}
