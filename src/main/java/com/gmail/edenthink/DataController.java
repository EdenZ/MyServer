package com.gmail.edenthink;

import com.gmail.edenthink.data.House;
import com.gmail.edenthink.data.PlayerData;
import com.gmail.edenthink.tools.DataAccess;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eden on 11/22/2015.
 */
public class DataController {
    private MyServer plugin;
    //Just for online player
    private List<PlayerData> playerDatas = new LinkedList<>();
    //All house playerDatas from files
    private List<House> houseDatas = new LinkedList<>();

    public DataController(MyServer plugin) {
        this.plugin = plugin;
    }

    public void playerJoin(Player player) {
        // FIXME: 11/22/2015 Create new player data or load player dat
        DataAccess pData = new DataAccess(plugin.getDataFolder().getAbsolutePath() + File.separator + "Player", player.getName() + ".yml");
    }

    public boolean buildNewHouse(Player player) {
        for (PlayerData d : playerDatas) {
            if (d.getName().equals(player.getName())) {
                if (d.getHouseID() != null) {
                    return false;
                }
                // FIXME: 11/22/2015 Create house data
                return true;
            }
        }
        return false;
    }

    /**
     * Search for the data of a player by name
     * @param name name of the player
     * @return data of the player. null if the player is offline or does not exist
     */
    public PlayerData getDataByName(String name) {
        for (PlayerData d : playerDatas) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Search house data by owner
     * @param owner name of the owner
     * @return house data belongs to the owner. null if not found.
     */
    public House getHouseByOwnerName(String owner) {
        for (House h : houseDatas) {
            if (h.getOwner().equals(owner)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Search house data by uuid
     * @param ID uuid
     * @return house data. null if not found.
     */
    public House getHouseByID(String ID) {
        for (House h : houseDatas) {
            if (h.getUuid().equals(ID)) {
                return h;
            }
        }
        return null;
    }
}
