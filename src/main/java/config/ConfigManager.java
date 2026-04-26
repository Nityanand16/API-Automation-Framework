package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties props = new Properties();

    static {
        try {
            // correct classpath loading (Maven-safe)
            InputStream input = ConfigManager.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties NOT found in resource folder");
            }

            props.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = props.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Missing config key: " + key);
        }

        return value.trim();
    }
}