package org.devathon.contest2016.abilities;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * @author Dean
 */
public class AbilityHandler implements Listener {
    public void onRightClick(PlayerInteractEntityEvent e) {
        if (e.getHand() == EquipmentSlot.HAND) {

        }
    }
}
