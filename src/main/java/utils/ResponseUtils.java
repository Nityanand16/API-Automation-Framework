package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseUtils {

    public static Integer extractId(Response response) {
        try {
            JsonPath jsonPath = response.jsonPath();

            Object idObj = jsonPath.get("id");

            if (idObj == null) {
                return null;
            }

            return Integer.parseInt(idObj.toString());

        } catch (Exception e) {
            System.out.println("⚠️ ID extraction failed: " + e.getMessage());
            return null;
        }
    }
}