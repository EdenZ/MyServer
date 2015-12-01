package com.gmail.edenthink.tweak;

import com.gmail.edenthink.tools.SQLManager;

/**
 * Player data manager
 */
public class PlayerData extends SQLManager {
    public static final String PLAYER = "player";
    public static final String P_NAME = "name";
    public static final String P_MAX_LEVEL = "max_level";
    public static final String P_EXP = "exp";
    public static final String P_RANK = "rank";
    public static final String P_GROUP = "group";

    /**
     *
     * @param player player
     * @param attribute data name
     * @return data
     */
    public int getInt(String player, String attribute) {
        return super.getInt(PLAYER, P_NAME, player, attribute);
    }

    /**
     *
     * @param player player
     * @param attribute data name
     * @return data
     */
    public String getString(String player, String attribute) {
        return super.getString(PLAYER, P_NAME, player, attribute);
    }

    /**
     *
     * @param player player name
     * @param attribute data name
     * @param value new data value
     */
    public void updateString(String player, String attribute, String value) {
        super.updateString(PLAYER, P_NAME, player, attribute, value);
    }

    /**
     *
     * @param player player name
     * @param attribute data name
     * @param value new data value
     */
    public void updateInt(String player, String attribute, int value) {
        super.updateInt(PLAYER, P_NAME, player, attribute, value);
    }

    /**
     *
     * @param player player name
     * @param attribute data name
     * @param value how much should the value be changed
     */
    public void intModify(String player, String attribute, int value) {
        super.intModify(PLAYER, P_NAME, player, attribute, value);
    }

    /**
     * insert new row for new player
     * @param player
     */
    public void insertRow(String player) {
        super.insertRow(PLAYER, P_NAME, player);
    }
}
