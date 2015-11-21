package com.gmail.edenthink.data;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Eden on 2015/11/21.
 */
public class Party implements ConfigurationSerializable {
    private Map<String, Position> member;
    private Map<String, Relation> internal;
    //KEY
    private String name;
    private int rank;
    private double point;
    @Nullable
    private UUID factoryID = null;

    /**
     * New party
     * @param player the player name who create this party
     * @param name the party name
     */
    public Party(String player,String name) {
        this.name = name;
        member = new HashMap<>();
        internal = new HashMap<>();
        member.put(player, Position.LEADER);
        rank = 0;
        point = 0;
        factoryID = null;
    }

    /**
     * Party from data file
     * @param map data
     */
    public Party(Map<String, Object> map) {
        //// FIXME: 2015/11/21
    }

    public Map<String, Position> getMember() {
        return member;
    }

    public void setMember(Map<String, Position> member) {
        this.member = member;
    }

    public Map<String, Relation> getInternal() {
        return internal;
    }

    public void setInternal(Map<String, Relation> internal) {
        this.internal = internal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public UUID getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(UUID factoryID) {
        this.factoryID = factoryID;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", name);
        map.put("Rank", rank);
        map.put("Point", point);
        if (factoryID != null) {
            map.put("Factory", factoryID.toString());
        }
        return map;
    }

    enum Position {
        MEMBER,
        MANAGER,
        LEADER;
    }

    enum Relation {
        FRIEND,
        ENEMY;
    }
}
