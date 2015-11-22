package com.gmail.edenthink;

import com.gmail.edenthink.data.PlayerData;
import org.bukkit.entity.Player;

/**
 * Created by Eden on 11/22/2015.
 */
public class GameTweak {
    public static MyServer plugin;

    /**
     * Called when player level up. Limit player level to the mex level they can be.
     * @param player the uped player
     */
    public static void limitMaxLevel(Player player) {
        PlayerData d = plugin.getDataController().getDataByName(player.getName());
        if (d == null) {
            return;
        }
        if (player.getLevel() > d.getMaxLevel()) {
            player.setLevel(d.getMaxLevel());
        }
    }

    public static boolean denyAllyDamage(Player damaged, Player damager) {
        // FIXME: 11/22/2015 True if the players are ally
        return false;
    }
}
