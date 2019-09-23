package com.starter.demo.controller;

import static org.hamcrest.Matchers.hasKey;

import com.starter.demo.base.BaseIntegrationTest;
import com.starter.demo.request.AuthRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

public class AuthenticationControllerTest extends BaseIntegrationTest {

    @Test
    public void loginForUser() {
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


    @Test
    public void loginForWrongPassword() {
        AuthRequest authRequest = new AuthRequest("user", "user3");
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .post("/login")
                .then()
                .statusCode(401);
    }

    @Test
    public void NoUser() {
        AuthRequest authRequest = new AuthRequest("user3", "user3");
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .post("/login")
                .then()
                .statusCode(401);
    }


    @Test
    public void loginForAdmin() {
        AuthRequest authRequest = new AuthRequest("admin", "admin");
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
