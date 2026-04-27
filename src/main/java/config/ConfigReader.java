package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(" Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = props.getProperty(key);

        if (value == null) {
            throw new RuntimeException(" Property not found: " + key);
        }

        return value;
    }

    public static String getBaseUrl() {
        return get("base.url");
    }
}