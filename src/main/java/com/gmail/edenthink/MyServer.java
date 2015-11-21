package com.gmail.edenthink;

import com.gmail.edenthink.data.Factory;
import com.gmail.edenthink.data.House;
import com.gmail.edenthink.data.Party;
import com.gmail.edenthink.tools.SerializableLocation;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * This is a template for bukkit plugin (Hooks with vault, titleManager)
 */
public class MyServer extends JavaPlugin {
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    //getter here

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
    }

    @Override
    public void onEnable() {
        //Register serializable
        ConfigurationSerialization.registerClass(SerializableLocation.class);
        ConfigurationSerialization.registerClass(House.class);
        ConfigurationSerialization.registerClass(Party.class);
        ConfigurationSerialization.registerClass(Factory.class);
        //enable managers

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
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
}
