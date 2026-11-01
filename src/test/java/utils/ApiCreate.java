package utils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiCreate {
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/api/auth";

    public static void createUser(String name, String email, String password) {

        Map<String, String> requestBody = Map.of(
                "name", name,
                "email", email,
                "password", password
        );


        given()
                .contentType(JSON)
                .body(requestBody)
                .post(BASE_URL + "/register")
                .then().statusCode(200);
    }

    public static void deleteUser(String email, String password) {

        Map<String, String> loginBody = Map.of("email", email, "password", password);

        String token = given()
                .contentType(JSON)
                .body(loginBody)
                .post(BASE_URL + "/login")
                .jsonPath().getString("accessToken");

        if (token != null) {
            given()
                    .header("Authorization", token)
                    .delete(BASE_URL + "/user")
                    .then().statusCode(202);
        }
    }
}