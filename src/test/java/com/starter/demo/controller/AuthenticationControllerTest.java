package com.starter.demo.controller;

import static org.hamcrest.Matchers.hasKey;

import com.starter.demo.BaseIntegrationTest;
import com.starter.demo.request.AuthRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

public class AuthenticationControllerTest extends BaseIntegrationTest {

    @Test
    public void login() {
        AuthRequest authRequest = new AuthRequest("user", "user");
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .post("/login")
                .then()
                .body("$", hasKey("access_token"))
                .statusCode(200);
    }

}
