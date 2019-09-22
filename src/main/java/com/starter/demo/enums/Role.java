package com.starter.demo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum Role implements IEnum<String> {
    ROLE_USER("user"), ROLE_ADMIN("admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getValue() {
        return this.role;
    }
}
