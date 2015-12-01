package com.gmail.edenthink.tweak;

import com.gmail.edenthink.MyServer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gmail.edenthink.tweak.PlayerData.P_MAX_LEVEL;

/**
 * Some rule in server:
 * 1.Max level is limit for every player
 * 2.Ore block is not allowed to place
 * 3.No block is allowed to place in res world
 */
public class GameTweak {
    public static MyServer plugin;
    public static PlayerData playerData;
    public final static List<Integer> machineID = new ArrayList<>(Arrays.asList(192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209));
    public final static List<Integer> oreID = new ArrayList<>(Arrays.asList(14, 15, 56, 129, 165, 166, 167, 168));

    /**
     * Called when player level up. Limit player level to the mex level they can be.
     * @param player the uped player
     */
    public static void limitMaxLevel(Player player) {
        int max = playerData.getInt(player.getName(), P_MAX_LEVEL);
        if (player.getLevel() > max) {
            player.setLevel(max);
        }
    }

    /**
     * Cancel event if player try to place ore
     * @param event block place event
     */
    @SuppressWarnings("deprecation")
    public static void noOrePlace(BlockPlaceEvent event) {
        if (!oreID.contains(event.getBlock().getTypeId())) {
            return;
        }
        event.getBlock().setType(Material.AIR);
    }

    /**
     * Cancel event if player place any block in res world except touch
     * @param event block place event
     */
    @SuppressWarnings("deprecation")
    public static void noPlaceInRes(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equalsIgnoreCase("res")) {
            if (event.getBlock().getTypeId() == 50) {
                return;
            }
            event.setCancelled(true);
        }
    }
}
