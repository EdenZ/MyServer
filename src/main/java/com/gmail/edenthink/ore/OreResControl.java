package com.gmail.edenthink.ore;

/**
 * Created by Eden on 2015/12/1.
 */
public class OreResControl {
    public static MinerData minerData = new MinerData();

    /**
     * check player has enough power to mine ore
     * @param player name
     * @return true or false
     */
    public static boolean powerCheck(String player) {
        int power = minerData.getPower(player);
        return power > 0;
    }

    /**
     * consume one power
     * @param player player name
     */
    public static void consumePower(String player) {
        minerData.powerModify(player, -1);
    }

    /**
     * check player has enough ticket to mine
     * @param player name of player
     * @return true of false
     */
    public static boolean ticketCheck(String player) {
        int ticket = minerData.getTicket(player);
        return ticket > 0;
    }

    /**
     * consume one ticket
     * @param player name of player
     */
    public static void consumeTicket(String player) {
        minerData.ticketModify(player, -1);
    }

    /**
     * regen the power
     * @param player name
     */
    public static void regenPower(String player) {
        int next = minerData.getPower(player) + minerData.getRegenRate(player);
        int max =  minerData.getMaxPower(player);
        if (next > max) {
            minerData.powerUpdate(player, max);
            return;
        }
        minerData.powerUpdate(player, next);
    }
}
