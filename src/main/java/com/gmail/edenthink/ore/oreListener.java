package com.gmail.edenthink.ore;

import com.gmail.edenthink.MyServer;
import com.gmail.edenthink.tweak.GameTweak;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Listener class
 */
public class OreListener implements Listener {

    public OreListener(MyServer plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void basicControl(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!GameTweak.oreID.contains(event.getBlock().getTypeId())) return;
        String player = event.getPlayer().getName();
        if (OreResControl.powerCheck(player)) {
            OreResControl.consumePower(player);
        } else if (OreResControl.ticketCheck(player)) {
            OreResControl.consumeTicket(player);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void newPlayer(PlayerJoinEvent event) {
        OreResControl.minerData.insertRow(event.getPlayer().getName());
    }
}
