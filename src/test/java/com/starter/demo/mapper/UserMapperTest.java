package com.starter.demo.mapper;

import static org.junit.Assert.assertEquals;

import com.starter.demo.base.BaseSpringBootTest;
import com.starter.demo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseSpringBootTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("user");
        assertEquals("user", user.getUsername());
        assertEquals(1, user.getRoleEnums().size());

    }
}
