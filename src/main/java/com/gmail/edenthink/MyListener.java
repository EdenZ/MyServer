package com.gmail.edenthink;

import com.gmail.edenthink.storage.SQLManager;
import com.gmail.edenthink.tweak.GameTweak;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

/**
 * Listener class for whole plugin
 */
public class MyListener implements Listener{
    private MyServer plugin;

    public MyListener(MyServer plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        //Tweak
        GameTweak.noOrePlace(event);
        GameTweak.noPlaceInRes(event);
    }

    @EventHandler
    public void onLevelUp(PlayerLevelChangeEvent event) {
        //Tweak
        GameTweak.limitMaxLevel(event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        //Tweak
        SQLManager.playerLogin(event.getPlayer().getName());
    }
}
