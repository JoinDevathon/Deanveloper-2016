package org.devathon.contest2016.abilities;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.devathon.contest2016.DevathonPlugin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Dean
 */
public class DefenseMatrix implements AbilityBase {
    @SuppressWarnings("deprecation")
    private ItemStack item = new ItemStack(Material.STAINED_GLASS, 1, DyeColor.BLUE.getWoolData());
    private Map<UUID, LocalDateTime> cooldown = new HashMap<>();
    private int usage = 100;
    private BukkitTask regenTask;
    private BukkitTask defenseTask;

    DefenseMatrix() {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§dDefense Matrix");
        item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void onRightClick(Player p) {
        // If the usage meter isn't empty and cooldown isn't in effect
        if (usage > 0 && cooldown.getOrDefault(p.getUniqueId(), LocalDateTime.MIN).isBefore(LocalDateTime.now())) {

            usage -= 3;
            cooldown.put(p.getUniqueId(), LocalDateTime.now().plus(100, ChronoUnit.MILLIS));



            if(defenseTask != null) {
                defenseTask.cancel();
            }
            defenseTask = new BukkitRunnable() {
                @Override
                public void run() {

                }
            }.runTaskLater(DevathonPlugin.getInstance(), 8L);

            // Start the regen task after a second of no defense matrix use
            if (regenTask != null) {
                regenTask.cancel();
            }
            regenTask = new BukkitRunnable() {
                @Override
                public void run() {
                    usage = Math.min(usage + 10, 100);

                    if (usage == 100) {
                        this.cancel();
                        regenTask = null;
                    }
                }
            }.runTaskTimer(DevathonPlugin.getInstance(), 20L, 2L);
        }
    }
}
