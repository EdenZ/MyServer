package com.gmail.edenthink.data;

import com.gmail.edenthink.tools.SerializableLocation;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eden on 2015/11/21.
 */
class House implements ConfigurationSerializable {
    private String owner;
    private int furniture = 0, room = 0, level = 0, mark = 0;
    private List<SerializableLocation> generators;

    /**
     * Constructor for new house
     *
     * @param owner player name
     */
    public House(Player owner) {
        this.owner = owner.getName();
        generators = new ArrayList<>();
        //The location is not good
        generators.add(new SerializableLocation(owner.getLocation().add(new Vector(0, -1, 0))));
    }

    /**
     * Constructor for house in data file
     *
     * @param map The data file
     */
    public House(Map<String, Object> map) {
        owner = (String) map.get("Owner");
        generators = (List<SerializableLocation>) map.get("Generator");
        furniture = (int) map.get("Furniture");
        room = (int) map.get("Room");
        level = (int) map.get("Level");
        mark = (int) map.get("Mark");
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("Owner", owner);
        map.put("Generator", generators);
        map.put("Furniture", furniture);
        map.put("Room", room);
        map.put("Level", level);
        map.put("Mark", mark);
        return map;
    }
}
