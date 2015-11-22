package com.gmail.edenthink.data;

import com.gmail.edenthink.tools.SerializableLocation;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

/**
 * Created by Eden on 2015/11/21.
 */
public class House implements ConfigurationSerializable {
    private String owner;
    //KEY
    private String uuid;
    private int furniture = 0, room = 0, level = 0, mark = 0;
    private List<SerializableLocation> generators;

    /**
     * Constructor for new House
     *
     * @param owner player name
     */
    public House(Player owner) {
        this.owner = owner.getName();
        uuid = UUID.randomUUID().toString();
        generators = new ArrayList<>();
        //The location is not good
        generators.add(new SerializableLocation(owner.getLocation().add(new Vector(0, -1, 0))));
    }

    /**
     * Constructor for House in data file
     *
     * @param map The data file
     */
    public House(Map<String, Object> map) {
        uuid = (String) map.get("UUID");
        owner = (String) map.get("Owner");
        generators = (List<SerializableLocation>) map.get("Generator");
        furniture = (int) map.get("Furniture");
        room = (int) map.get("Room");
        level = (int) map.get("Level");
        mark = (int) map.get("Mark");
    }

    public String getOwner() {
        return owner;
    }

    public int getFurniture() {
        return furniture;
    }

    public int getRoom() {
        return room;
    }

    public int getLevel() {
        return level;
    }

    public int getMark() {
        return mark;
    }

    public List<SerializableLocation> getGenerators() {
        return generators;
    }

    public void setFurniture(int furniture) {
        this.furniture = furniture;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setGenerators(List<SerializableLocation> generators) {
        this.generators = generators;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("UUID", uuid);
        map.put("Owner", owner);
        map.put("Generator", generators);
        map.put("Furniture", furniture);
        map.put("Room", room);
        map.put("Level", level);
        map.put("Mark", mark);
        return map;
    }
}
