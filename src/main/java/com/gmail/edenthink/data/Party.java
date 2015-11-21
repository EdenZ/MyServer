package com.gmail.edenthink.data;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * Created by Eden on 2015/11/21.
 */
public class Party implements ConfigurationSerializable {
    private Map<String, Position> member;
    private Map<String, Relation> internal;
    private int rank;
    private double point;
    private Factory factory;

    @Override
    public Map<String, Object> serialize() {
        return null;
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
