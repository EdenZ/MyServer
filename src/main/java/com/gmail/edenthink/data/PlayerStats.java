package com.gmail.edenthink.data;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * Created by Eden on 2015/11/21.
 */
public class PlayerStats implements ConfigurationSerializable{
    private int rank;
    private House house;
    private int maxLevel;

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

}
