package com.gmail.edenthink.data;

import com.gmail.edenthink.tools.SerializableLocation;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Eden on 2015/11/21.
 */
public class Factory implements ConfigurationSerializable {
    private String party;
    private int rent;
    //KEY
    private UUID uuid;
    private SerializableLocation location;

    /**
     * For new
     * @param party Owner party
     * @param location the location
     */
    public Factory(String party, Location location) {
        this.party = party;
        this.location = new SerializableLocation(location);
        uuid = UUID.randomUUID();
        rent = 0;
    }

    public Factory(Map<String, Object> map) {
        //// FIXME: 2015/11/21
    }
    public String getParty() {
        return party;
    }

    public int getRent() {
        return rent;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public SerializableLocation getLocation() {
        return location;
    }

    public void setLocation(SerializableLocation location) {
        this.location = location;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("Party", party);
        map.put("rent", rent);
        map.put("UUID", uuid.toString());
        map.put("Location", location);
        return map;
    }
}
