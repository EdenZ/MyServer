package com.gmail.edenthink.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Eden on 2015/11/26.
 */
public class Driver {
    private static Connection connection = null;
    private static final String DATABASE_URI = "jdbc:sqlite:./plugins/MyServer/storage.db";

    /**
     * Connect to database
     */
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DATABASE_URI);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Get the connection
     * @return database connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    /**
     * Disconnect
     */
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Cannot close connection");
            }
        }
    }
}
