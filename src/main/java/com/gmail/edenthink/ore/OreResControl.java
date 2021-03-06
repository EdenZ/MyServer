package com.gmail.edenthink.ore;

/**
 * Class for helping
 * 1.Consume and regen power
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
    public static void regenPower(String player, int amount) {
        int next = minerData.getPower(player) + amount;
        int max =  minerData.getMaxPower(player);
        if (next > max) {
            minerData.powerUpdate(player, max);
            return;
        }
        minerData.powerUpdate(player, next);
    }
}
