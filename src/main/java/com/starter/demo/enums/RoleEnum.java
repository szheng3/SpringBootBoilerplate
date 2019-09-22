package com.starter.demo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum RoleEnum implements IEnum<String> {
    ROLE_USER("user"), ROLE_ADMIN("admin");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String getValue() {
        return this.role;
    }
}
