package com.starter.demo.controller;

import static com.starter.demo.util.TokenStore.tokenStore;

import com.starter.demo.BaseIntegrationTest;
import com.starter.demo.enums.RoleEnum;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

public class ResourceControllerTest extends BaseIntegrationTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void user() {
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header(tokenStore.get(RoleEnum.ROLE_USER))
                .get("/resource/user")
                .then()
                .statusCode(200);

    }
    @Test
    public void userForbidden() {
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get("/resource/user")
                .then()
                .statusCode(401);

    }


    @Test
    public void admin() {
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header(tokenStore.get(RoleEnum.ROLE_ADMIN))
                .get("/resource/admin")
                .then()
                .statusCode(200);
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header(tokenStore.get(RoleEnum.ROLE_USER))
                .get("/resource/admin")
                .then()
                .statusCode(401);

    }

    @Test
    public void userOrAdmin() {
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header(tokenStore.get(RoleEnum.ROLE_ADMIN))
                .get("/resource/user-or-admin")
                .then()
                .statusCode(200);
        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header(tokenStore.get(RoleEnum.ROLE_USER))
                .get("/resource/user-or-admin")
                .then()
                .statusCode(200);
    }
}
