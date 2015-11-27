package com.gmail.edenthink.storage;

import java.sql.*;

/**
 * Created by Eden on 2015/11/26.
 */
public class Driver {
    private static Connection connection = null;

    public static void connect() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ignored) {
            }
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:../MyServer/storage.db");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public static int executeSQL(String sql) {
        int number = -1;
        if (connection == null) {
            connect();
        }
        try (Statement s = connection.createStatement()) {
            number = s.executeUpdate(sql);
        } catch (SQLException ignored) {
        }
        return number;
    }

    public static ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        if (connection == null) {
            connect();
        }
        try (Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            resultSet = s.executeQuery(sql);
        } catch (SQLException ignored) {
        }
        return resultSet;
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
