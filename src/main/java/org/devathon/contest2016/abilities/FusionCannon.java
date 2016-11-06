package org.devathon.contest2016.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
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
    private BukkitTask slowUntilNextShot;

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
                shootFrom.setDirection(p.getLocation().getDirection());
                shootFrom.setYaw(p.getLocation().getYaw());
                shootFrom.setPitch(p.getLocation().getPitch() - 30); // raise pitch by to adjust for third person

                // Add the direction the player is facing to prevent intersection with the mech
                shootFrom.add(p.getLocation().getDirection());

                // Spawn 10 arrows with high velocity and wide spread
                for (int i = 0; i < 10; i++) {
                    Arrow a = DevathonPlugin.getMainWorld().spawnArrow(shootFrom, shootFrom.getDirection(), 3, 15);
                    EntityProperties.addProperty(a.getUniqueId(), "fusionCannon");
                }

                DevathonPlugin.getMainWorld().playSound(shootFrom, Sound.ENTITY_GENERIC_EXPLODE, .25f, 2f);

                p.setWalkSpeed(.1f);
                if(slowUntilNextShot != null) {
                    slowUntilNextShot.cancel();
                }
                slowUntilNextShot = new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.setWalkSpeed(.2f);
                    }
                }.runTaskLater(DevathonPlugin.getInstance(), 5L);

                // Start a short cooldown and switch the hand being used
                cooldown.put(p.getUniqueId(), LocalDateTime.now().plus(150, ChronoUnit.MILLIS));
                whichHand.put(p.getUniqueId(), !isRightHand);
            }
        }
    }
}
