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
    public static final String REGEN_RATE = "regen_rate";

    /**
     * get player power
     * @param player name
     * @return power
     */
    public int getPower(String player) {
        return getInt(TABLE, NAME, player, MINER_POWER);
    }

    /**
     * get player max power
     * @param player name
     * @return max power
     */
    public int getMaxPower(String player) {
        return getInt(TABLE, NAME, player, MAX_POWER);
    }

    /**
     * get player ticket
     * @param player name
     * @return ticket
     */
    public int getTicket(String player) {
        return getInt(TABLE, NAME, player, TICKET);
    }

    /**
     * get player regen rate
     * @param player name
     * @return regen rate
     */
    public int getRegenRate(String player) {
        return getInt(TABLE, NAME, player, REGEN_RATE);
    }

    /**
     * +/- value to player power
     * @param player name
     * @param value value
     */
    public void powerModify(String player, int value) {
        intModify(TABLE, NAME, player, MINER_POWER, value);
    }

    /**
     * set power to value
     * @param player name
     * @param value value
     */
    public void powerUpdate(String player, int value) {
        updateInt(TABLE, NAME, player, MINER_POWER, value);
    }

    /**
     * +/- ticket a value
     * @param player name
     * @param value value
     */
    public void ticketModify(String player, int value) {
        intModify(TABLE, NAME, player, TICKET, value);
    }

    /**
     * insert new row for new player
     * @param player name
     */
    public void insertRow(String player) {
        super.insertRow(TABLE, NAME, player);
    }
}
