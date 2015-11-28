package com.gmail.edenthink.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eden on 2015/11/27.
 */
public class SQLManager {
    public static final String PLAYER ="Player",
            NAME = "Name",
        MACHINE_NUMBER = "MachineNumber",
        MAX_NUMBER = "MaxNumber",
        MAX_LEVEL = "MaxLevel",
        MONSTER_KILLED = "MonsterKilled",
        ORE_MINED = "OreMined",
        PARTY = "Party";


    public static void createTable() {
        String sql = "CREATE TABLE " + PLAYER + "( " +
                NAME + "  CHAR(50)  PRIMARY KEY, " +
                MACHINE_NUMBER + " INT DEFAULT 0, " +
                MAX_NUMBER + " INT DEFAULT 35, " +
                MAX_LEVEL + "  INT DEFAULT 5, " +
                MONSTER_KILLED + " INT DEFAULT 0, " +
                ORE_MINED + " INT DEFAULT 0, " +
                PARTY + " CHAR(50) " +
                ");";
        try {
            executeSQL(sql);
        } catch (SQLException e) {
            System.err.println("SQL:Error code: " + e.getErrorCode());
            System.err.println("    Message: " + e.getMessage());
        }
    }

    public static int executeSQL(String sql) throws SQLException {
        int number = -1;
        try (Statement s = Driver.getConnection().createStatement()) {
            number = s.executeUpdate(sql);
        }
        return number;
    }

    public static Map<String, Object> getPlayerData(String player) {
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT *" +
                " FROM " + PLAYER +
                " WHERE " + NAME + " = \"" + player + "\";";
        ResultSet resultSet = null;
        try (Statement s = Driver.getConnection().createStatement()) {
            try {
                resultSet = s.executeQuery(sql);
                if (resultSet.next()) {
                    map.put(MACHINE_NUMBER, resultSet.getInt(MACHINE_NUMBER));
                    map.put(MAX_NUMBER, resultSet.getInt(MAX_NUMBER));
                    map.put(MAX_LEVEL, resultSet.getInt(MAX_LEVEL));
                    map.put(MONSTER_KILLED, resultSet.getInt(MONSTER_KILLED));
                    map.put(ORE_MINED, resultSet.getInt(ORE_MINED));
                    map.put(PARTY, resultSet.getString(PARTY));
                }
            } catch (SQLException e) {
                System.err.println("SQL:Error code: " + e.getErrorCode());
                System.err.println("    Message: " + e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void updateString(String player, String attribute, String value) {
        String sql = "UPDATE " + PLAYER +
                " SET " + attribute + " = \"" + value + "\" " +
                " WHERE " + NAME + " = \"" + player + "\";";
        try {
            executeSQL(sql);
        } catch (SQLException e) {
            System.err.println("SQL:Error code: " + e.getErrorCode());
            System.err.println("    Message: " + e.getMessage());
        }
    }

    public static void updateInt(String player, String attribute, int value) {
        String sql = "UPDATE " + PLAYER +
                " SET " + attribute + " = " + value + " " +
                " WHERE " + NAME + " = \"" + player + "\";";
        try {
            executeSQL(sql);
        } catch (SQLException e) {
            System.err.println("SQL:Error code: " + e.getErrorCode());
            System.err.println("    Message: " + e.getMessage());
        }
    }

    public static void playerLogin(String player) {
        String sql = "INSERT INTO " + PLAYER + " ( " + NAME + " ) " +
                "VALUES (\"" + player + "\");";
        try {
            executeSQL(sql);
        } catch (SQLException e) {
            System.err.println("SQL:Error code: " + e.getErrorCode());
            System.err.println("    Message: " + e.getMessage());
        }
    }
}
