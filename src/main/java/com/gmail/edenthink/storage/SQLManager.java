package com.gmail.edenthink.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eden on 2015/11/27.
 */
public class SQLManager {
    public static final String PLAYER ="Player",
            NAME = "Name",
            MAX_LEVEL = "MaxLevel",
            MONSTER_KILLED = "MonsterKilled",
            ORE_MINED = "OreMined",
            HOUSE_WORTH = "HouseWorth",
            PARTY = "Party";

    /**
     * create table in database if the table does not exist, or do nothing
     */
    public static void createTable() {
        String sql = "CREATE TABLE " + PLAYER + "( " +
                NAME + "  CHAR(50)  PRIMARY KEY, " +
                MAX_LEVEL + "  INT DEFAULT 5, " +
                MONSTER_KILLED + " INT DEFAULT 0, " +
                ORE_MINED + " INT DEFAULT 0, " +
                HOUSE_WORTH + " INT DEFAULT 0, " +
                PARTY + " CHAR(50) " +
                ");";
        executeSQL(sql);
    }

    /**
     * execute a sql statement
     * @param sql statement
     * @return number of row being impacted
     */
    public static int executeSQL(String sql) {
        int number = -1;
        try (Statement s = Driver.getConnection().createStatement()) {
            number = s.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("SQL:Error code: " + e.getErrorCode());
            System.err.println("    Message: " + e.getMessage());
        }
        return number;
    }

    /**
     * Running a query to get one data of a player
     * @param player whose data
     * @param attribute what data
     * @return data
     */
    public static Object getPlayerData(String player, String attribute) {
        Object re = null;
        String sql = "SELECT " + attribute +
                " FROM " + PLAYER +
                " WHERE " + NAME + " = \"" + player + "\";";
        ResultSet resultSet = null;
        try (Statement s = Driver.getConnection().createStatement()) {
            try {
                resultSet = s.executeQuery(sql);
                if (resultSet.next()) {
                    re = resultSet.getObject(attribute);
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
            System.err.println("SQL:Error code: " + e.getErrorCode());
            System.err.println("    Message: " + e.getMessage());
        }
        return re;
    }

    /**
     * Get the int data of player from database
     * @param player whose data
     * @param attribute what data
     * @return int data
     */
    public static int getInt(String player, String attribute) {
        return (int) getPlayerData(player, attribute);
    }

    /**
     * Get string data of player from database
     * @param player whose data
     * @param attribute what data
     * @return string data
     */
    public static String getString(String player, String attribute) {
        return (String) getPlayerData(player, attribute);
    }

    /**
     * Update a string value
     * @param player the player
     * @param attribute what kind of data
     * @param value the value
     */
    public static void updateString(String player, String attribute, String value) {
        String sql = "UPDATE " + PLAYER +
                " SET " + attribute + " = \"" + value + "\" " +
                " WHERE " + NAME + " = \"" + player + "\";";
        executeSQL(sql);
    }

    /**
     * Updata a int value
     * @param player the player
     * @param attribute what kind of data
     * @param value the value
     */
    public static void updateInt(String player, String attribute, int value) {
        String sql = "UPDATE " + PLAYER +
                " SET " + attribute + " = " + value + " " +
                " WHERE " + NAME + " = \"" + player + "\";";
        executeSQL(sql);
    }

    /**
     * Create new row if the player is new, or do nothing
     * @param player who join the server
     */
    public static void playerLogin(String player) {
        String sql = "INSERT INTO " + PLAYER + " ( " + NAME + " ) " +
                "VALUES (\"" + player + "\");";
        executeSQL(sql);
    }
}
