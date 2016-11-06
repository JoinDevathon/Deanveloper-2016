package org.devathon.contest2016.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.DevathonPlugin;

import java.time.LocalDateTime;

/**
 * @author Dean
 */
public class Booster implements AbilityBase {
    private static final ItemStack item = new ItemStack(Material.FIREBALL);
    private LocalDateTime cooldown = LocalDateTime.MIN;

    public Booster() {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Boosters");
        item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void onRightClick(Player p) {
        if (cooldown.isBefore(LocalDateTime.now())) {
            new BukkitRunnable() {
                int ticksRan = 0;

                @Override
                public void run() {
                    Location toGo = p.getLocation();
                    toGo.setPitch(toGo.getPitch() - 20); // account for third person
                    p.setVelocity(toGo.getDirection().multiply(.5));
                    DevathonPlugin.getMainWorld().playSound(toGo, Sound.ENTITY_GENERIC_EXPLODE, .1f, .5f);

                    ticksRan++;
                    if(ticksRan >= 60) {
                        this.cancel();
                    }
                }
            }.runTaskTimer(DevathonPlugin.getInstance(), 0L, 1L);

            cooldown = LocalDateTime.now().plusSeconds(8);
        }
    }
}
