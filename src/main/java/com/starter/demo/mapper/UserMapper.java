package com.starter.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.demo.domain.User;import java.util.List;import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    List<User> findAllByAge(@Param("age") Integer age);

    List<User> findAllByEmail(@Param("email") String email);
}