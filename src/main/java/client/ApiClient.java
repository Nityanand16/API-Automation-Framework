package client;

import config.ConfigManager;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static final AllureRestAssured ALLURE_FILTER = new AllureRestAssured();

    private static String getBaseUrl() {
        return ConfigManager.get("base.url");
    }

    private static String getToken() {
        String token = ConfigManager.get("token");
        return (token != null) ? token : "";
    }

    /**
     * Thread-safe request builder
     */
    private static RequestSpecification request() {

        RequestSpecification spec = RestAssured
                .given()
                .baseUri(getBaseUrl())
                .contentType("application/json")
                .accept("application/json")
                .filter(ALLURE_FILTER);

        // attach token only if present
        if (!getToken().isEmpty()) {
            spec.header("Authorization", "Bearer " + getToken());
        }

        return spec;
    }

    public static Response post(String endpoint, Object body) {

        Allure.step("POST " + endpoint);
        attachRequest(body);

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
        attachRequest(body);

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

    private static void attachRequest(Object body) {
        Allure.addAttachment(
                "Request Body",
                body != null ? body.toString() : "null");
    }

    private static void attachResponse(Response response) {
        if (response == null) {
            Allure.addAttachment("Response", "NULL RESPONSE");
            return;
        }

        Allure.addAttachment("Status Code",
                String.valueOf(response.getStatusCode()));

        Allure.addAttachment("Response Body",
                response.getBody() != null
                        ? response.getBody().asPrettyString()
                        : "EMPTY BODY");
    }
}