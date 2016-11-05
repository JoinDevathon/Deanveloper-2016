package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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

        mainWorld.getEntitiesByClass(ArmorStand.class).forEach(Entity::remove);

        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    SteVA.getPlayers().get(p.getUniqueId()).update();
                }
            }
        }.runTaskTimer(this, 2L, 2L);
    }

    @Override
    public void onDisable() {

    }
}

