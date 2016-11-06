package org.devathon.contest2016.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * @author Dean
 */
public interface AbilityBase {

    UUID getId();

    ItemStack getItem();

    // Even though there is a new AbilityBase per player, we use this method to prevent
    // memory leaks from storing players.
    void onRightClick(Player p);
}
