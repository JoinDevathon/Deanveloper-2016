package org.devathon.contest2016.abilities;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.devathon.contest2016.DevathonPlugin;

/**
 * @author Dean
 */
public class DefenseMatrix implements AbilityBase {
    @SuppressWarnings("deprecation")
    private ItemStack item = new ItemStack(Material.STAINED_GLASS, 1, DyeColor.BLUE.getWoolData());
    private int usage = 100;
    private BukkitTask regenTask;

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
        if (usage > 0) {


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
