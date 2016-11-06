package org.devathon.contest2016.abilities;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * @author Dean
 */
public class Placeholder implements AbilityBase {
    public Placeholder(Player player) {
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public void onRightClick(Player p) {
    }
}
