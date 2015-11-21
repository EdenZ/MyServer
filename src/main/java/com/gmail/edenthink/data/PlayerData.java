package com.gmail.edenthink.data;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Eden on 2015/11/21.
 */
public class PlayerData implements ConfigurationSerializable{
    //KEY
    private String name;
    private int rank;
    @Nullable
    private UUID houseID = null;
    private int maxLevel;
    @Nullable
    private String party =null;

    /**
     * New player
     * @param name player name
     */
    public PlayerData(String name) {
        this.name = name;
        rank = 0;
        maxLevel = 5;
    }

    /**
     * Player stats from data file
     * @param map data
     */
    public PlayerData(Map<String, Object> map) {
        //// FIXME: 2015/11/21
    }

    //Getter and setter
    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public UUID getHouseID() {
        return houseID;
    }

    public void setHouseID(UUID houseID) {
        this.houseID = houseID;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", name);
        map.put("Rank", rank);
        map.put("MaxLevel", maxLevel);
        if (houseID != null) {
            map.put("HouseID", houseID.toString());
        }
        if (party != null) {
            map.put("Party", party);
        }
        return map;
    }
}
