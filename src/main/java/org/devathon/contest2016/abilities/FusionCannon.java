package org.devathon.contest2016.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.EntityProperties;
import org.devathon.contest2016.SteVa;

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
        // Check to see if the player is on cooldown
        if (cooldown.getOrDefault(p.getUniqueId(), LocalDateTime.MIN).isBefore(LocalDateTime.now())) {

            // Check if the player has a mech
            SteVa steva = SteVa.getPlayers().get(p.getUniqueId());
            if (steva != null) {
                // Get which hand to shoot from
                boolean isRightHand = whichHand.getOrDefault(p.getUniqueId(), false);
                Location shootFrom = isRightHand ? steva.getModel().rightHandLoc() : steva.getModel().leftHandLoc();
                // Add the direction the player is facing to prevent intersection with the mech
                shootFrom.add(p.getLocation().getDirection());

                // Spawn 10 arrows with high velocity and wide spread
                for (int i = 0; i < 10; i++) {
                    Arrow a = DevathonPlugin.getMainWorld().spawnArrow(shootFrom, p.getLocation().getDirection(), 3, 20);
                    EntityProperties.addProperty(a.getUniqueId(), "removeOnHit");
                }

                // Start a short cooldown and switch the hand being used
                cooldown.put(p.getUniqueId(), LocalDateTime.now().plus(150, ChronoUnit.MILLIS));
                whichHand.put(p.getUniqueId(), !isRightHand);
            }
        }
    }
}
