package com.starter.demo;


import static com.starter.demo.util.TokenStore.tokenStore;

import com.starter.demo.enums.RoleEnum;
import com.starter.demo.request.AuthRequest;
import com.starter.demo.response.AuthResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DemoApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = "service.url = http://localhost:8069")
public class BaseIntegrationTest {

    @ClassRule
    public final static SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private Environment environment;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Before
    public void setUp() {
        String port = environment.getProperty("local.server.port");
        RestAssured.baseURI = "http://localhost:" + port;
        if (tokenStore.isEmpty()) {
            logger.info("generate token");
            tokenStore.put(RoleEnum.ROLE_USER, jwtToken("user", "user"));
            tokenStore.put(RoleEnum.ROLE_ADMIN, jwtToken("admin", "admin"));

        }


    }

    private Header jwtToken(String user, String password) {
        AuthRequest authRequest = new AuthRequest(user, password);
        String token = new StringBuilder()
                .append("Bearer ")
                .append(
                        RestAssured.given()
                                .when()
                                .contentType(ContentType.JSON)
                                .body(authRequest)
                                .post("/login")
                                .then()
                                .statusCode(200)
                                .extract()
                                .as(AuthResponse.class)
                                .getAccess_token()
                )
                .toString();
        return new Header("Authorization", token);


    }

}
