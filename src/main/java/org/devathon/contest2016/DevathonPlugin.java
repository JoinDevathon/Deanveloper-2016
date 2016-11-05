package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.abilities.AbilityHandler;

public class DevathonPlugin extends JavaPlugin {
    private static DevathonPlugin instance;
    private static World mainWorld;
    private IdleModel dummy;

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
        Bukkit.getPluginManager().registerEvents(new AbilityHandler(), this);

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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                Player p = (Player) sender;
                if(dummy != null) {
                    dummy.setLoc(p.getLocation());
                } else {
                    dummy = new IdleModel(p.getLocation());
                }
            } else if (args[0].equalsIgnoreCase("clear")){
                dummy.destroy();
                dummy = null;
            }
        }
        return true;
    }
}

