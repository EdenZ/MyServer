package com.gmail.edenthink.ore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eden on 2015/12/1.
 */
public class MinerTaskManger {




    class PowerRegen extends BukkitRunnable {
        @Override
        public void run() {

            for (Player p : Bukkit.getOnlinePlayers()) {
                OreResControl.regenPower(p.getName());
            }
        }
    }
}
