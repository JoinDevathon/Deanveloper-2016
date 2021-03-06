package org.devathon.contest2016;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.abilities.AbilityBase;
import org.devathon.contest2016.misc.EntityProperties;

/**
 * @author Dean
 */
public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Create a mech for the player
                SteVa steVa = new SteVa(e.getPlayer());

                // Give the player invisibility
                e.getPlayer().addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.INVISIBILITY,
                                Integer.MAX_VALUE,  // duration
                                0,                  // amplifier
                                true,               // ambient
                                false               // particles
                        ), true
                );

                // Populate their inventory
                e.getPlayer().getInventory().clear();
                for (AbilityBase ability : steVa.getAbilities()) {
                    if (ability.getItem() == null) {
                        continue;
                    }
                    e.getPlayer().getInventory().addItem(ability.getItem());
                }
            }
        }.runTaskLater(DevathonPlugin.getInstance(), 5L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        SteVa.get(e.getPlayer()).destroy();
    }

    @EventHandler
    public void onLeave(PlayerKickEvent e) {
        SteVa.get(e.getPlayer()).destroy();
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        if (EntityProperties.hasProperty(e.getEntity(), "fusionCannon")) {
            e.getEntity().remove();
        }
        EntityProperties.remove(e.getEntity());
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (EntityProperties.hasProperty(e.getDamager().getUniqueId(), "fusionCannon")) {
            e.setDamage(1.5);
        }
    }

    @EventHandler
    public void entityDeath(EntityDeathEvent e) {
        EntityProperties.remove(e.getEntity());
    }
}
