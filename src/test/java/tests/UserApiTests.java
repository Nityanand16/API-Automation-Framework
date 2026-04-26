package tests;

import client.ApiClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

@Feature("User API Tests")
public class UserApiTests {

        private Integer userId; // stores created user ID for reuse

        // =========================
        // 1. CREATE USER
        // =========================
        @Test(priority = 1)
        @Story("Create User")
        @Description("Create a new user and store user ID")
        public void createUserTest() {

                // Step 1: Create request payload
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("name", "User_" + System.currentTimeMillis());
                requestBody.put("email", "user_" + System.currentTimeMillis() + "@mail.com");
                requestBody.put("gender", "male");
                requestBody.put("status", "active");

                // Step 2: Call API
                Response response = ApiClient.post("/users", requestBody);

                // Step 3: Validate response
                Assert.assertEquals(response.getStatusCode(), 201, "User not created");

                // Step 4: Extract user ID
                userId = ResponseUtils.extractId(response);
                Assert.assertNotNull(userId, "User ID is null");
        }

        // =========================
        // 2. GET USER
        // =========================
        @Test(priority = 2, dependsOnMethods = "createUserTest")
        @Story("Get User")
        @Description("Fetch created user by ID")
        public void getUserTest() {

                Response response = ApiClient.get("/users/" + userId);

                Assert.assertEquals(response.getStatusCode(), 200, "User not found");
        }

        // =========================
        // 3. UPDATE USER
        // =========================
        @Test(priority = 3, dependsOnMethods = "createUserTest")
        @Story("Update User")
        @Description("Update existing user details")
        public void updateUserTest() {

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("name", "UpdatedUser_" + System.currentTimeMillis());
                requestBody.put("email", "updated_" + System.currentTimeMillis() + "@mail.com");
                requestBody.put("status", "active");

                Response response = ApiClient.put("/users/" + userId, requestBody);

                Assert.assertEquals(response.getStatusCode(), 200, "User update failed");
        }

        // =========================
        // 4. DELETE USER
        // =========================
        @Test(priority = 4, dependsOnMethods = "createUserTest")
        @Story("Delete User")
        @Description("Delete created user")
        public void deleteUserTest() {

                Response response = ApiClient.delete("/users/" + userId);

                Assert.assertEquals(response.getStatusCode(), 204, "User deletion failed");
        }

        // =========================
        // 5. NEGATIVE TEST
        // =========================
        @Test(priority = 5)
        @Story("Negative Test")
        @Description("Validate invalid user ID returns error")
        public void getInvalidUserTest() {

                Response response = ApiClient.get("/users/999999999");

                Assert.assertTrue(
                                response.getStatusCode() == 404 || response.getStatusCode() == 400,
                                "Unexpected response for invalid user");
        }
}