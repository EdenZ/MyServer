package com.gmail.edenthink.tweak;

import com.gmail.edenthink.MyServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

/**
 * Listener class for whole plugin
 */
public class TweakListener implements Listener{

    public TweakListener(MyServer plugin) {
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
        GameTweak.newPlayerJoin(event.getPlayer().getName());
    }
}
