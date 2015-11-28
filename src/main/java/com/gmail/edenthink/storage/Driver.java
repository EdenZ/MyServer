package com.gmail.edenthink.storage;

import java.sql.*;

/**
 * Created by Eden on 2015/11/26.
 */
public class Driver {
    private static Connection connection = null;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:storage.db");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

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
