package org.devathon.contest2016;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.abilities.Ability;

/**
 * @author Dean
 */
public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                new SteVa(e.getPlayer());
                e.getPlayer().addPotionEffect(
                        new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2, true, false), true
                );
                e.getPlayer().getInventory().clear();
                for(Ability a : Ability.values()) {
                    if (a.getAbility().getItem() == null) {
                        continue;
                    }
                    e.getPlayer().getInventory().addItem(a.getAbility().getItem());
                }
            }
        }.runTaskLater(DevathonPlugin.getInstance(), 5L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        SteVa.getPlayers().get(e.getPlayer().getUniqueId()).destroy();
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        if (EntityProperties.hasProperty(e.getEntity(), "removeOnHit")) {
            e.getEntity().remove();
        }
    }
}
