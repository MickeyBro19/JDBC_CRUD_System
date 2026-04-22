package com.mickey.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    public static final int MAX_CONNECTIONS=3;
    public static final List<Connection> availableConnections=new ArrayList<>();
    public static final List<Connection> usedConnections=new ArrayList<>();

    static{
        try{
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                availableConnections.add(DBConnection.getConnection());
            }

        }
        catch (Exception e){
            System.out.println("Error getting connection");
            e.printStackTrace();
        }
    }

    public synchronized static  Connection getConnection(){
        while(availableConnections.isEmpty()){
            try{
                System.out.println("Waiting for available Connections:...");
                ConnectionPool.class.wait();

            }catch(InterruptedException e){
                throw new RuntimeException("Thread Interrupted",e);
            }

        }
        Connection conn=availableConnections.remove(0);
        usedConnections.add(conn);

        System.out.println("Connection allocated. Available: " + availableConnections.size());
        AppLogger.log("Connection allocated. Available: " + availableConnections.size());
        return conn;
    }

    public synchronized static void releaseConnection(Connection conn) {
        usedConnections.remove(conn);
        availableConnections.add(conn);

        System.out.println("Connection released. Available: " + availableConnections.size());
        AppLogger.log("Connection released. Available: " + availableConnections.size());
        ConnectionPool.class.notifyAll();

    }

}
