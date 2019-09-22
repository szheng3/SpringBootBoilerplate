package com.starter.demo.util;

import com.starter.demo.enums.RoleEnum;
import io.restassured.http.Header;
import java.util.HashMap;
import java.util.Map;

public class TokenStore {

    public static Map<RoleEnum, Header> tokenStore = new HashMap<>();

}
