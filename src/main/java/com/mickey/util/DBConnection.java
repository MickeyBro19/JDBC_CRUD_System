package com.mickey.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection
                    (
                    ConfigLoader.get("db.url"),
                    ConfigLoader.get("db.user"),
                    ConfigLoader.get("db.password")
                    );
        } catch (Exception e) {
            AppLogger.error("Error Connecting to DB "+e);
            throw new RuntimeException("Error Connecting to DB");
        }
    }
}
