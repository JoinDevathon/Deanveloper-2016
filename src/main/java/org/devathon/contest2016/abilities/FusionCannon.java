package org.devathon.contest2016.abilities;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.SteVA;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Dean
 */
public class FusionCannon implements AbilityBase {
    private Map<UUID, LocalDateTime> cooldown = new HashMap<>();
    private Map<UUID, Boolean> whichHand = new HashMap<>();
    private ItemStack item = new ItemStack(Material.IRON_BARDING);

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void onRightClick(PlayerInteractEntityEvent e) {
        if (cooldown.get(e.getPlayer().getUniqueId()).isBefore(LocalDateTime.now())) {
            SteVA steva = SteVA.getPlayers().get(e.getPlayer().getUniqueId());
            if (steva != null) {
                boolean rightHand = whichHand.getOrDefault(e.getPlayer().getUniqueId(), false);
            }
        }
    }
}
