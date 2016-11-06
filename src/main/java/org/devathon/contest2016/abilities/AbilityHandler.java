package org.devathon.contest2016.abilities;

import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * @author Dean
 */
public class AbilityHandler implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.HAND) {
            onRightClick((PlayerEvent) e);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent e) {
        if (e.getHand() == EquipmentSlot.HAND) {
            onRightClick((PlayerEvent) e);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (e.getHand() == EquipmentSlot.HAND) {
            onRightClick((PlayerEvent) e);
        }
    }

    public void onRightClick(PlayerEvent e) {
        for (Ability a : Ability.values()) {
            if(a.getAbility().getItem().equals(e.getPlayer().getInventory().getItemInMainHand())) {
                a.getAbility().onRightClick(e.getPlayer());
                if(e instanceof Cancellable) {
                    ((Cancellable) e).setCancelled(true);
                }
                break;
            }
        }
    }
}
