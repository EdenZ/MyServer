package com.gmail.edenthink.ore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Command for /miner
 *  buy 1   -- $20 for 30 tickets
 *  buy 2   -- $30 for 50 tickets
 *  buy 3   -- $50 for 100 tickets
 *  show    -- show current power/ max power and current ticket number
 */
public class MinerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        return false;
    }
}
