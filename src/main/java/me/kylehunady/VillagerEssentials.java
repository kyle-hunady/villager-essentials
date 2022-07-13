package me.kylehunady;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class VillagerEssentials extends JavaPlugin {
    private static VillagerEssentials plugin;

    @Override
    public void onEnable() {
        plugin = this;
        LogInfo("Registering listeners.");
        getServer().getPluginManager().registerEvents(new GossipHandler(), this);
        getServer().getPluginManager().registerEvents(new ZombieLootHandler(), this);
        getServer().getPluginManager().registerEvents(new ZombificationHandler(), this);
        LogInfo("Listeners registered.");
        LogInfo("VillagerEssentials enabled!");
    }

    @Override
    public void onDisable() {
        LogInfo("VillagerEssentials disabled!");
    }

    private void LogInfo(String msg) {
        plugin.getLogger().log(Level.INFO, msg);
    }

    public static VillagerEssentials getPlugin() {
        return plugin;
    }
}