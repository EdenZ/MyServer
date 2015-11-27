package com.gmail.edenthink.storage;

import java.sql.ResultSet;

/**
 * Created by Eden on 2015/11/27.
 */
public class SQLManager {
    public static void createTable() {
        String sql = "CREATE TABLE Player( " +
                "  Name  CHAR(50)  PRIMARY KEY, " +
                "  MachineNumber INT DEFAULT 0, " +
                "  MaxLevel  INT DEFAULT 5, " +
                "  MonsterKilled INT DEFAULT 0, " +
                "  OreMined INT DEFAULT 0, " +
                "  Party CHAR(50) " +
                ");";
        Driver.executeSQL(sql);
    }

    public static ResultSet getPlayerData(String player) {
        ResultSet resultSet = null;
        String sql = "SELECT *" +
                "FROM Player" +
                "WHERE Name = \"" + player + "\";";
        return resultSet;
    }
}
