package com.gmail.edenthink;

import com.gmail.edenthink.storage.SQLManager;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Eden on 11/22/2015.
 */
public class GameTweak {
    public static MyServer plugin;
    public static List<Integer> machineID = new ArrayList<>(Arrays.asList(192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209));
    public static List<Integer> oreID = new ArrayList<>(Arrays.asList(14, 15, 56, 129, 165, 166, 167, 168));

    /**
     * Called when player level up. Limit player level to the mex level they can be.
     * @param player the uped player
     */
    public static void limitMaxLevel(Player player) {
        int max = (int) SQLManager.getPlayerData(player.getName()).get(SQLManager.MAX_LEVEL);
        if (player.getLevel() > max) {
            player.setLevel(max);
        }
    }

    @SuppressWarnings("deprecation")
    public static void limitMacNum(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!machineID.contains(event.getBlock().getTypeId())) {
            return;
        }
        int max = (int) SQLManager.getPlayerData(event.getPlayer().getName()).get(SQLManager.MAX_NUMBER);
        int current = (int) SQLManager.getPlayerData(event.getPlayer().getName()).get(SQLManager.MACHINE_NUMBER);
        if (current >= max) {
            event.setCancelled(true);
            return;
        }
        SQLManager.updateInt(event.getPlayer().getName(), SQLManager.MACHINE_NUMBER, current + 1);
    }

    @SuppressWarnings("deprecation")
    public static void limitMacNum(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!machineID.contains(event.getBlock().getTypeId())) {
            return;
        }
        int current = (int) SQLManager.getPlayerData(event.getPlayer().getName()).get(SQLManager.MACHINE_NUMBER);
        SQLManager.updateInt(event.getPlayer().getName(), SQLManager.MACHINE_NUMBER, current - 1);
    }

    public static void countMonsterKilled(EntityDeathEvent event) {
        if (event.getEntity() instanceof Monster) {
            String player = event.getEntity().getKiller().getName();
            SQLManager.updateInt(player, SQLManager.MONSTER_KILLED, (int) SQLManager.getPlayerData(player).get(SQLManager.MONSTER_KILLED) + 1);
        }
    }

    @SuppressWarnings("deprecation")
    public static void countOreMined(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!oreID.contains(event.getBlock().getTypeId())) {
            return;
        }
        String player = event.getPlayer().getName();
        SQLManager.updateInt(player, SQLManager.ORE_MINED, (int) SQLManager.getPlayerData(player).get(SQLManager.ORE_MINED) + 1);
    }

    public static boolean denyAllyDamage(Player damaged, Player damager) {
        // FIXME: 11/22/2015 True if the players are ally
        return false;
    }
}
