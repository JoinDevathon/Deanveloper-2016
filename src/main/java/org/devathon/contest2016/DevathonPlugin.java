package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DevathonPlugin extends JavaPlugin {
    private static DevathonPlugin instance;
    private static World mainWorld;

    public static DevathonPlugin getInstance() {
        return instance;
    }

    public static World getMainWorld() {
        return mainWorld;
    }

    @Override
    public void onEnable() {
        instance = this;
        mainWorld = Bukkit.getWorlds().get(0);
    }

    @Override
    public void onDisable() {

    }
}

