package ru.gb.diplom.user;



import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.gb.diplom.DiplomApplication;


@SpringBootTest(classes = {DiplomApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(scripts = "/clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
public class UserResourceTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void should_create_user() throws IOException {
        //given
        byte[] body = loadResource("/user.json");
        RequestSpecification request = RestAssured.given()
                .body(body)
                .header(new Header("content-type","application/json"));
        //when
        Response response = request.post(getUserEndpoint());
        //then
        System.out.println(response.body().print());
        response.then()
                .statusCode(200);
    }

    @Test
    public void should_return_created_user() throws IOException {
        //given
        byte[] body = loadResource("/user.json");
        String id = RestAssured.given()
                .body(body)
                .header(new Header("content-type", "application/json"))
                .post(getUserEndpoint()).getBody().print();
        //when
        Response response = RestAssured.given()
                .auth()
                .basic("admin", "admin")
                .get(getUserEndpoint() + "/" + id);
        //then
        response.then()
                .statusCode(200)
                .body("username", Matchers.equalTo("magician"))
                .body("firstname", Matchers.equalTo("John"))
                .body("lastname", Matchers.equalTo("Doe"))
                .body("email", Matchers.equalTo("magician@gmail.com"))
                .body("roles[0].id", Matchers.equalTo(2));
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("std.ds","UTF-8"));
    }

    private String getUserEndpoint() {
        return "/users";
    }

    private byte[] loadResource(String resourcePath) throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(resourcePath);
        return IOUtils.toByteArray(resourceAsStream);
    }
}