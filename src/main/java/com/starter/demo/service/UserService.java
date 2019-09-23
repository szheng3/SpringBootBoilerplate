package com.starter.demo.service;

import com.starter.demo.domain.User;

public interface UserService {

    User findByUsername(String username);
}
