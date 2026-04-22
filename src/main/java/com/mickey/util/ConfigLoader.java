package com.mickey.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
        private static Properties properties=new Properties();
    static {
        try {
            InputStream input=ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                AppLogger.error("config.properties not found in resources folder");
            }

            properties.load(input);

            AppLogger.log("Config loaded successfully");
        } catch(Exception e) {
            AppLogger.error("Error Fetching resources "+e);
        }
    }
    public static String get(String key){
        return properties.getProperty(key);
    }
}
