package org.devathon.contest2016.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.SteVA;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    FusionCannon() {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§dFusion Cannon");
        item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void onRightClick(PlayerInteractEntityEvent e) {
        if (cooldown.get(e.getPlayer().getUniqueId()).isBefore(LocalDateTime.now())) {
            SteVA steva = SteVA.getPlayers().get(e.getPlayer().getUniqueId());
            if (steva != null) {
                boolean isRightHand = whichHand.getOrDefault(e.getPlayer().getUniqueId(), false);
                Location toShootFrom = isRightHand ? steva.getModel().rightHandLoc() : steva.getModel().leftHandLoc();

                for (int i = 0; i < 10; i++) {
                    DevathonPlugin.getMainWorld().spawnArrow(toShootFrom, e.getPlayer().getLocation().getDirection(), 1, 30);
                }

                cooldown.put(e.getPlayer().getUniqueId(), LocalDateTime.now().plus(150, ChronoUnit.MILLIS));
                whichHand.put(e.getPlayer().getUniqueId(), !isRightHand);
            }
        }
    }
}
