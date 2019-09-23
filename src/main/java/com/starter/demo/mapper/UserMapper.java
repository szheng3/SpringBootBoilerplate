package com.starter.demo.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.demo.domain.User;

public interface UserMapper extends BaseMapper<User> {
    User findByUsername(@Param("username")String username);

}
