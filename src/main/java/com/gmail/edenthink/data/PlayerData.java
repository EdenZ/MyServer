package com.gmail.edenthink.data;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * Created by Eden on 2015/11/21.
 */
public class PlayerData implements ConfigurationSerializable{
    private int rank;
    private House House;
    private int maxLevel;
    private Party party;

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
