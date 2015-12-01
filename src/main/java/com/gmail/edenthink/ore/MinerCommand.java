package com.gmail.edenthink.ore;

import com.gmail.edenthink.MyServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command for /miner
 *  buy 1   -- $20 for 30 tickets
 *  buy 2   -- $30 for 50 tickets
 *  buy 3   -- $50 for 100 tickets
 *  show    -- show current power/ max power and current ticket number
 */
public class MinerCommand implements CommandExecutor {
    private MyServer plugin;

    public MinerCommand(MyServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("miner")) {
            if (strings.length == 1) {
                //miner show
                if (strings[0].equalsIgnoreCase("show")) {
                    player.sendMessage(String.format("Power: %3d / %3d\nTicket: %3d", OreResControl.minerData.getPower(player.getName()), OreResControl.minerData.getMaxPower(player.getName()), OreResControl.minerData.getTicket(player.getName())));
                    return true;
                }
            } else if (strings.length == 2) {
                if (strings[0].equalsIgnoreCase("buy")) {
                    int cost = 0, tickets = 0;
                    if (strings[1].equalsIgnoreCase("1")) {
                        cost = 20;
                        tickets = 30;
                    } else if (strings[1].equalsIgnoreCase("2")) {
                        cost = 30;
                        tickets = 50;
                    } else if (strings[1].equalsIgnoreCase("3")) {
                        cost = 50;
                        tickets = 100;
                    } else return false;
                    if (MyServer.getEconomy().withdrawPlayer(player, cost).transactionSuccess()) {
                        OreResControl.minerData.ticketModify(player.getName(), tickets);
                        player.sendMessage(String.format("BIU - %d \nTicket + %d ", cost, tickets));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
