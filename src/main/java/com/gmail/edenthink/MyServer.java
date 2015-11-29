package com.gmail.edenthink;

import com.gmail.edenthink.storage.Driver;
import com.gmail.edenthink.storage.SQLManager;
import com.gmail.edenthink.tools.SerializableLocation;
import com.gmail.edenthink.tweak.GameTweak;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MyServer extends JavaPlugin {
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    //getter here
    public static Logger getLog() {
        return log;
    }

    public static Chat getChat() {
        return chat;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static Permission getPermission() {
        return permission;
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

    //Put new Executor class here
    private void initialExecutor() {
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        SQLManager.createTable();
        //Register serializable
        ConfigurationSerialization.registerClass(SerializableLocation.class);
        //enable managers
        GameTweak.plugin = this;
        //vault
        initialVault();
        //Listener and executor
        MyListener listener = new MyListener(this);
        initialExecutor();
    }

    @Override
    public void onDisable() {
        Driver.disconnect();
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
}
