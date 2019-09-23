package com.starter.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.demo.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    User findByUsername(@Param("username")String username);

}
