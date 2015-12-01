package com.gmail.edenthink.ore;

import com.gmail.edenthink.MyServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eden on 2015/12/1.
 */
public class MinerTaskManger {
    private MyServer plugin;

    public MinerTaskManger(MyServer plugin) {
        this.plugin = plugin;
        new PowerRegen().runTaskTimer(plugin, 1200, 1200);
    }

    class PowerRegen extends BukkitRunnable {
        @Override
        public void run() {

            for (Player p : Bukkit.getOnlinePlayers()) {
                OreResControl.regenPower(p.getName());
            }
        }
    }
}
