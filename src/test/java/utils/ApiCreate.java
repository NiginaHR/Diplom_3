package utils;

import static io.restassured.RestAssured.given;

public class ApiCreate {
    private static final String URL = "https://qa-stellarburgers.education-services.ru/api/auth";

    public static void createUser(String name, String email, String password) {
        given()
                .contentType("application/json")
                .body("{\"name\":\"" + name + "\", \"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .post(URL + "/register")
                .then().statusCode(200);
    }

    public static void deleteUser(String email, String password) {
        String token = given()
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .post(URL + "/login")
                .jsonPath().getString("accessToken");

        given()
                .header("Authorization", token)
                .delete("https://qa-stellarburgers.education-services.ru/api/auth/user");
    }
}