package client;

import config.ConfigManager;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static String getBaseUrl() {
        return ConfigManager.get("base.url");
    }

    private static RequestSpecification request() {
        return RestAssured
                .given()
                .baseUri(getBaseUrl())
                .header("Authorization", "Bearer " + ConfigManager.get("token"))
                .contentType("application/json")
                .filter(new AllureRestAssured()); // keeps REST logs in report
    }

    public static Response post(String endpoint, Object body) {

        Allure.step("POST " + endpoint);
        Allure.addAttachment("Request Body", body != null ? body.toString() : "null");

        Response response = request()
                .body(body)
                .post(endpoint);

        attachResponse(response);
        return response;
    }

    public static Response get(String endpoint) {

        Allure.step("GET " + endpoint);

        Response response = request()
                .get(endpoint);

        attachResponse(response);
        return response;
    }

    public static Response put(String endpoint, Object body) {

        Allure.step("PUT " + endpoint);
        Allure.addAttachment("Request Body", body != null ? body.toString() : "null");

        Response response = request()
                .body(body)
                .put(endpoint);

        attachResponse(response);
        return response;
    }

    public static Response delete(String endpoint) {

        Allure.step("DELETE " + endpoint);

        Response response = request()
                .delete(endpoint);

        attachResponse(response);
        return response;
    }

    private static void attachResponse(Response response) {
        Allure.addAttachment("Status Code", String.valueOf(response.getStatusCode()));
        Allure.addAttachment("Response Body", response.getBody().asPrettyString());
    }
}