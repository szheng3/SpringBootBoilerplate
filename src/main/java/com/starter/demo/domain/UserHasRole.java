package com.starter.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_has_role")
public class UserHasRole {

    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    @TableId(value = "role_id_role", type = IdType.INPUT)
    private Integer roleIdRole;
}