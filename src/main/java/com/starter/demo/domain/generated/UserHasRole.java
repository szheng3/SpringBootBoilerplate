package com.starter.demo.domain.generated;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.starter.demo.domain.Root;
import lombok.Data;

@Data
@TableName(value = "user_has_role")
public class UserHasRole extends Root {

    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    @TableId(value = "role_id_role", type = IdType.INPUT)
    private Integer roleIdRole;
}
