package org.devathon.contest2016.abilities;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * @author Dean
 */
public class AbilityHandler implements Listener {
    public void onRightClick(PlayerInteractEntityEvent e) {
        if (e.getHand() == EquipmentSlot.HAND) {
            for (Ability a : Ability.values()) {
                if(a.getAbility().getItem().equals(e.getPlayer().getInventory().getItemInMainHand())) {
                    a.getAbility().onRightClick(e);
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (e.getHand() == EquipmentSlot.HAND) {
            for (Ability a : Ability.values()) {
                if(a.getAbility().getItem().equals(e.getPlayer().getInventory().getItemInMainHand())) {
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }
}
