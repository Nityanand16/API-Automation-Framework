package utils;

import models.User;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DataFactory {

    // 🔹 Random Name
    public static String getRandomName() {
        return "User_" + UUID.randomUUID().toString().substring(0, 5);
    }

    // 🔹 Random Email (always unique)
    public static String getRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 5) + "@mail.com";
    }

    // 🔹 Random Gender
    public static String getGender() {
        return ThreadLocalRandom.current().nextBoolean() ? "male" : "female";
    }

    // 🔹 Random Status
    public static String getStatus() {
        return ThreadLocalRandom.current().nextBoolean() ? "active" : "inactive";
    }

    // FULL USER OBJECT (BEST PRACTICE)
    public static User createUser() {
        User user = new User();
        user.setName(getRandomName());
        user.setEmail(getRandomEmail());
        user.setGender(getGender());
        user.setStatus("active"); // keep active for create API stability
        return user;
    }

    // UPDATE USER OBJECT (for PUT requests)
    public static User updateUser() {
        User user = new User();
        user.setName("Updated_" + UUID.randomUUID().toString().substring(0, 4));
        user.setStatus("inactive");
        return user;
    }
}