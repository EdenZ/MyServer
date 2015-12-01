package com.gmail.edenthink.ore;

import com.gmail.edenthink.tools.SQLManager;

/**
 * Created by Eden on 2015/12/1.
 */
public class MinerData extends SQLManager {
    public static final String TABLE = "miner";
    public static final String NAME = "name";
    public static final String MINER_POWER = "miner_power";
    public static final String MAX_POWER = "max_power";
    public static final String TICKET = "ticket";

    public String getString(String player, String attribute) {
        return super.getString(TABLE, NAME, player, attribute);
    }

    public int getPower(String player) {
        return getInt(TABLE, NAME, player, MINER_POWER);
    }

    public int getTicket(String player) {
        return getInt(TABLE, NAME, player, TICKET);
    }

    public void powerModify(String player, int value) {
        super.intModify(TABLE, NAME, player, MINER_POWER, value);
    }

    public void ticketModify(String player, int value) {
        super.intModify(TABLE, NAME, player, TICKET, value);
    }

    public void insertRow(String value) {
        super.insertRow(TABLE, NAME, value);
    }
}
