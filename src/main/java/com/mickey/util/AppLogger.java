package com.mickey.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogger {
    private static final String LOGGER_FILE="app.log";
    public static void log(String message){
        try
            (FileWriter file = new FileWriter(LOGGER_FILE, true)){
            String logTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            file.write(logTime + ": " + message + "\n");
        } catch (IOException e) {
            System.out.println("Logging Failed "+e.getMessage());
        }

    }
    public static void error(String message){
        log("ERROR: "+message);
    }
}
