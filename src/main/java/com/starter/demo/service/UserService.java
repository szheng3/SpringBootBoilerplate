package com.starter.demo.service;

import com.starter.demo.domain.User;
import com.starter.demo.mapper.UserMapper;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Mono<User> findByUsername(String username) {
        return Mono.fromSupplier(() -> userMapper.findByUsername(username)
        ).subscribeOn(Schedulers.elastic());

    }

}
