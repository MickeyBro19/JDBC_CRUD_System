package com.mickey.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static final String URL="jdbc:postgresql://localhost:5432/Student";
    public static final String USER="postgres";
    public static final String PASSWORD="0000";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
