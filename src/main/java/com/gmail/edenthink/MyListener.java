package com.gmail.edenthink;

import com.gmail.edenthink.storage.SQLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

/**
 * Created by Eden on 2015/11/28.
 */
public class MyListener implements Listener{
    private MyServer plugin;

    public MyListener(MyServer plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        //Limit the max number of machine
        GameTweak.limitMacNum(event);
    }

    @EventHandler
    public void onLevelUp(PlayerLevelChangeEvent event) {
        //Limit the max level
        GameTweak.limitMaxLevel(event.getPlayer());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        //Counting ore mined
        GameTweak.countOreMined(event);
        GameTweak.limitMacNum(event);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        //Counting kill
        GameTweak.countMonsterKilled(event);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        SQLManager.playerLogin(event.getPlayer().getName());
    }
}
