package com.gmail.edenthink.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eden on 2015/11/21.
 */
public class SerializableLocation implements ConfigurationSerializable {
    private double x, y, z;
    private String world;
    private Location location;

    public SerializableLocation(Map<String, Object> map) {
        x = (double) map.get("X");
        y = (double) map.get("Y");
        z = (double) map.get("Z");
        world = (String) map.get("World");
        location = new Location(Bukkit.getWorld(world), x, y, z);
    }

    public SerializableLocation(Location location) {
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        world = location.getWorld().getName();
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("X", x);
        map.put("Y", y);
        map.put("Z", z);
        map.put("World", world);
        return map;
    }
}
