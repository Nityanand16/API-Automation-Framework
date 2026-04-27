package tests;

import client.ApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ResponseUtils;
import utils.RetryAnalyzer;

import java.util.HashMap;
import java.util.Map;

public class UserApiTests {

        // 🔥 Thread-safe user storage (important for parallel execution)
        private final ThreadLocal<Integer> userId = new ThreadLocal<>();

        // 🔹 Create payload dynamically
        private Map<String, Object> createUserPayload() {
                Map<String, Object> payload = new HashMap<>();
                long timestamp = System.currentTimeMillis();

                payload.put("name", "TestUser_" + timestamp);
                payload.put("email", "testuser_" + timestamp + "@mail.com");
                payload.put("gender", "male");
                payload.put("status", "active");

                return payload;
        }

        // 🔹 Update payload
        private Map<String, Object> updateUserPayload() {
                Map<String, Object> payload = new HashMap<>();
                payload.put("name", "UpdatedUser");
                payload.put("status", "inactive");
                return payload;
        }

        // 🔥 SETUP (retry-safe + parallel-safe)
        @BeforeMethod(alwaysRun = true)
        public void setupUser() {

                int retry = 0;
                int maxRetry = 3;
                Response response;
                Integer id = null;

                while (retry < maxRetry) {

                        response = ApiClient.post("/users", createUserPayload());

                        id = ResponseUtils.extractId(response);

                        if (id != null) {
                                userId.set(id);
                                break;
                        }

                        retry++;
                        System.out.println("🔁 Retry creating user: " + retry);
                }

                Assert.assertNotNull(userId.get(), "User creation failed in setup");
                System.out.println("🆔 Created User ID: " + userId.get());
        }

        // 🔥 CLEANUP (safe for parallel + always runs)
        @AfterMethod(alwaysRun = true)
        public void cleanupUser() {

                Integer id = userId.get();

                if (id != null) {
                        System.out.println("🧹 Cleaning user: " + id);
                        try {
                                ApiClient.delete("/users/" + id);
                        } catch (Exception e) {
                                System.out.println("⚠ Cleanup failed: " + e.getMessage());
                        }
                }

                userId.remove();
        }

        // 🔹 GET user test
        @Test(retryAnalyzer = RetryAnalyzer.class)
        public void getUserTest() {

                Response response = ApiClient.get("/users/" + userId.get());

                Assert.assertEquals(response.statusCode(), 200, "Get user failed");
                System.out.println("✔ Fetched User ID: " + userId.get());
        }

        // 🔹 UPDATE user test
        @Test(retryAnalyzer = RetryAnalyzer.class)
        public void updateUserTest() {

                Response response = ApiClient.put(
                                "/users/" + userId.get(),
                                updateUserPayload());

                Assert.assertEquals(response.statusCode(), 200, "User update failed");
                System.out.println("✔ Updated User ID: " + userId.get());
        }

        // 🔹 DELETE user test
        @Test(retryAnalyzer = RetryAnalyzer.class)
        public void deleteUserTest() {

                Response response = ApiClient.delete("/users/" + userId.get());

                Assert.assertEquals(response.statusCode(), 204, "User delete failed");
                System.out.println("✔ Deleted User ID: " + userId.get());

                userId.remove(); // avoid double cleanup
        }

        // 🔹 NEGATIVE test (no dependency)
        @Test
        public void getInvalidUserTest() {

                Response response = ApiClient.get("/users/999999999");

                Assert.assertEquals(response.statusCode(), 404, "Invalid user test failed");
                System.out.println("✔ Negative test passed");
        }
}