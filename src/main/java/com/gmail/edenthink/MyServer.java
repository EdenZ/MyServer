package com.gmail.edenthink;

import com.gmail.edenthink.tools.SerializableLocation;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a template for bukkit plugin (Hooks with vault, titleManager)
 */
public class MyServer extends JavaPlugin {
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    private static final Logger log = Logger.getLogger("Minecraft");
    private FileConfiguration data;
    private File dataFile;
    private FileConfiguration config;
    private File cfile;
    private FileConfiguration langConfig;
    private File languageFile;
    private FileConfiguration houseData;
    private File houseFile;
    private HouseManager houseManager;

    //getter here
    public static Logger getLog() {
        return log;
    }

    public File getCfile() {
        return cfile;
    }

    public FileConfiguration getData() {
        return data;
    }

    public File getDataFile() {
        return dataFile;
    }

    public File getLanguageFile() {
        return languageFile;
    }

    public FileConfiguration getLangConfig() {
        return langConfig;
    }

    //Three methods to hook with vault
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration( Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    //Three methods called when enabling
    private void initialVault() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
    }

    //Put new listener class here
    private void initialListener() {

    }

    //Put new Executor class here
    private void initialExecutor() {

    }

    //Build up the default config file when config file does not exist
    private void setupConfig() {
        //Config
        config = getConfig();
        cfile = new File(getDataFolder(), "config.yml");
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        //Data file
        dataFile = new File(getDataFolder(), "data.yml");
        if(!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                log.info(ChatColor.RED + "Could not create data file!");
            }
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        //Language file
        languageFile = new File(getDataFolder(), "lang.yml");
        if(!languageFile.exists()) {
            try {
                languageFile.createNewFile();
            } catch (IOException e) {
                log.info(ChatColor.RED + "Could not create data file!");
            }
        }
        langConfig = YamlConfiguration.loadConfiguration(languageFile);
        saveDefaultConfig();
    }



    public void saveData() {
        try {
            data.save(dataFile);
        } catch (IOException e) {
            log.info(ChatColor.RED + "Could not save data file!");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void reloadHouseData() {
        if (houseFile == null) {
            houseFile = new File(getDataFolder(), "house.yml");
        }
        houseData = YamlConfiguration.loadConfiguration(houseFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(this.getResource("house.yml"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            //houseData.setDefaults(defConfig);
        }
    }

    public FileConfiguration getHouseData() {
        if (houseData == null) {
            reloadHouseData();
        }
        return houseData;
    }

    public void saveHouseData() {
        if (houseData == null || houseFile == null) {
            return;
        }
        try {
            getHouseData().save(houseFile);
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + houseFile, ex);
        }
    }

    @Override
    public void onEnable() {
        //Register serializable
        ConfigurationSerialization.registerClass(SerializableLocation.class);
        ConfigurationSerialization.registerClass(HouseManager.House.class);
        //enable managers
        houseManager = new HouseManager(this);
        //setup config
        setupConfig();
        //vault
        initialVault();
        //Listener and executor
        initialListener();
        initialExecutor();
    }

    @Override
    public void onDisable() {
        saveData();
        saveHouseData();
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
}
