package com.gmail.edenthink;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * This is a template for bukkit plugin (Hooks with vault, titleManager)
 */
public class MyReward extends JavaPlugin {
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    private static final Logger log = Logger.getLogger("Minecraft");
    private FileConfiguration data;
    private File dfile;
    private FileConfiguration config;
    private File cfile;
    private FileConfiguration langConfig;
    private File lfile;

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

    public File getDfile() {
        return dfile;
    }

    public File getLfile() {
        return lfile;
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
        config = getConfig();
        cfile = new File(getDataFolder(), "config.yml");

        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        dfile = new File(getDataFolder(), "data.yml");

        if(!dfile.exists()) {
            try {
                dfile.createNewFile();
            } catch (IOException e) {
                log.info(ChatColor.RED + "Could not create data file!");
            }
        }

        data = YamlConfiguration.loadConfiguration(dfile);

        lfile = new File(getDataFolder(), "lang.yml");

        if(!lfile.exists()) {
            try {
                lfile.createNewFile();
            } catch (IOException e) {
                log.info(ChatColor.RED + "Could not create data file!");
            }
        }

        langConfig = YamlConfiguration.loadConfiguration(lfile);

        saveDefaultConfig();

    }

    public void saveData() {
        try {
            data.save(dfile);
        } catch (IOException e) {
            log.info(ChatColor.RED + "Could not save data file!");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(dfile);
    }

    @Override
    public void onEnable() {
        setupConfig();
        initialVault();

        initialListener();
        initialExecutor();
    }

    @Override
    public void onDisable() {
        saveData();
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
}
