package utils;

import io.restassured.response.Response;

public class ResponseUtils {

    public static Integer extractId(Response response) {
        try {
            Object idObj = response.jsonPath().get("id");

            if (idObj == null)
                return null;

            String idStr = idObj.toString();

            if (idStr.equalsIgnoreCase("null") || idStr.equals("[null]") || idStr.isEmpty()) {
                return null;
            }

            return Integer.valueOf(idStr);

        } catch (Exception e) {
            System.out.println(" ID extraction failed: " + e.getMessage());
            return null;
        }
    }
}