package org.devathon.contest2016.abilities;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Dean
 */
public interface AbilityBase {
    public ItemStack getItem();
    public void onRightClick(PlayerInteractEntityEvent e);
}
