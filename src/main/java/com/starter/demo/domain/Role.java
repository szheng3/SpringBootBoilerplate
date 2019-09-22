package com.starter.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "role")
public class Role extends Root{
    @TableId(value = "id_role", type = IdType.AUTO)
    private Integer idRole;

    @TableField(value = "role")
    private com.starter.demo.enums.Role role;
}
