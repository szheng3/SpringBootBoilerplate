package com.starter.demo.mapper;

import static org.junit.Assert.*;

import com.starter.demo.DemoApplication;
import com.starter.demo.DemoApplicationTests;
import com.starter.demo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("user");
        assertEquals("user", user.getUsername());
        assertEquals(1, user.getRoleEnums().size());

    }
}
