package org.devathon.contest2016.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.SteVa;
import org.devathon.contest2016.misc.EntityProperties;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * @author Dean
 */
public class FusionCannon implements AbilityBase {
    private final UUID id;
    private LocalDateTime cooldown = LocalDateTime.MIN;
    private boolean isRightHand = false;
    private ItemStack item = new ItemStack(Material.IRON_BARDING);

    FusionCannon(Player p) {
        this.id = p.getUniqueId();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§dFusion Cannon");
        item.setItemMeta(meta);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void onRightClick(Player p) {
        // Check to see if the player is on cooldown
        if (cooldown.isBefore(LocalDateTime.now())) {

            // Check if the player has a mech
            SteVa steva = SteVa.getPlayers().get(p.getUniqueId());
            if (steva != null) {
                // Get which hand to shoot from
                Location shootFrom = isRightHand ? steva.getModel().rightHandLoc() : steva.getModel().leftHandLoc();
                shootFrom.setDirection(p.getLocation().getDirection());
                shootFrom.setYaw(p.getLocation().getYaw());
                shootFrom.setPitch(p.getLocation().getPitch() - 30); // raise pitch by to adjust for third person

                // Add the direction the player is facing to prevent intersection with the mech
                // (the fancy pitch magic is to make sure it doesn't include up/down
                Location loc = p.getLocation();
                loc.setPitch(0);
                shootFrom.add(loc.getDirection().normalize());

                // Spawn 10 arrows with high velocity and wide spread
                for (int i = 0; i < 10; i++) {
                    Arrow a = DevathonPlugin.getMainWorld().spawnArrow(shootFrom, shootFrom.getDirection(), 2, 15);
                    EntityProperties.addProperty(a.getUniqueId(), "fusionCannon");
                }

                DevathonPlugin.getMainWorld().playSound(shootFrom, Sound.ENTITY_GENERIC_EXPLODE, .25f, 2f);

                // Start a short cooldown and switch the hand being used
                cooldown = LocalDateTime.now().plus(150, ChronoUnit.MILLIS);
                isRightHand = !isRightHand;
            }
        }
    }
}
