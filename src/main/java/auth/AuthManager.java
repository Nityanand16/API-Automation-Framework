package auth;

import config.ConfigManager;

public final class AuthManager {
    private AuthManager() {
    }

    public static String getBearerToken() {
        return ConfigManager.get("apiToken");
    }
}