package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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

        Bukkit.getPluginManager().registerEvents(new MainListener(), this);

        new BukkitRunnable() {

            @Override
            public void run() {
                SteVA.getPlayers().values().forEach(SteVA::update);
            }
        }.runTaskTimer(this, 1L, 1L);
    }

    @Override
    public void onDisable() {
        mainWorld.getEntitiesByClass(ArmorStand.class).forEach(Entity::remove);
    }
}

