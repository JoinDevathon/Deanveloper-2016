package org.devathon.contest2016.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
    public void onRightClick(Player p) {
        if (cooldown.getOrDefault(p.getUniqueId(), LocalDateTime.MIN).isBefore(LocalDateTime.now())) {
            SteVA steva = SteVA.getPlayers().get(p.getUniqueId());
            if (steva != null) {
                boolean isRightHand = whichHand.getOrDefault(p.getUniqueId(), false);
                Location toShootFrom = isRightHand ? steva.getModel().rightHandLoc() : steva.getModel().leftHandLoc();

                for (int i = 0; i < 10; i++) {
                    DevathonPlugin.getMainWorld().spawnArrow(toShootFrom, p.getLocation().getDirection(), 1, 30);
                }

                cooldown.put(p.getUniqueId(), LocalDateTime.now().plus(150, ChronoUnit.MILLIS));
                whichHand.put(p.getUniqueId(), !isRightHand);
            }
        }
    }
}
