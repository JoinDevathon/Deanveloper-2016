package org.devathon.contest2016.abilities;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Dean
 */
public class Placeholder implements AbilityBase {
    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public void onRightClick(PlayerInteractEntityEvent e) {}
}
