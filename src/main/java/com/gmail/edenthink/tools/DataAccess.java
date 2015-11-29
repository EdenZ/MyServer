package com.gmail.edenthink.tools;

import com.gmail.edenthink.tweak.GameTweak;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Eden on 11/23/2015.
 */
public class DataAccess {
    private FileConfiguration data = null;
    private File dataFile = null;

    private String fileName;
    private String parent;

    public DataAccess(String parent, String fileName) {
        this.fileName = fileName;
        this.parent = parent;
    }

    public void reloadData() {
        if (dataFile == null) {
            dataFile = new File(parent, fileName);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getData() {
        if (data == null) {
            reloadData();
        }
        return data;
    }

    public void saveData() {
        if (data == null || dataFile == null) {
            return;
        }
        try {
            getData().save(dataFile);
        } catch (IOException e) {
            GameTweak.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + dataFile, e);
        }
    }
}
