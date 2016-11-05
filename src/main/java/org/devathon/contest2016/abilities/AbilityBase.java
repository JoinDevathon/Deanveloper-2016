package org.devathon.contest2016.abilities;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Dean
 */
interface AbilityBase {
    ItemStack getItem();
    void onRightClick(PlayerInteractEntityEvent e);
}
