package org.devathon.contest2016.abilities;

import net.minecraft.server.v1_10_R1.*;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.misc.EntityProperties;
import org.devathon.contest2016.model.DefenseMatrixModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Dean
 */
public class DefenseMatrix implements AbilityBase {
    @SuppressWarnings("deprecation")
    private static final ItemStack item = new ItemStack(Material.STAINED_GLASS, 1, DyeColor.BLUE.getWoolData());
    private LocalDateTime cooldown = LocalDateTime.MIN;
    private int usage = 100;
    private BukkitTask regenTask;
    private BukkitTask defenseTask;

    DefenseMatrix() {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§dDefense Matrix");
        item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void onRightClick(Player p) {
        // If the usage meter isn't empty and "cooldown" isn't in effect
        if (usage > 0 && cooldown.isBefore(LocalDateTime.now())) {
            // This will run it down in ~4 seconds
            setUsage(Math.max(0, getUsage() - 16), p);
            cooldown = LocalDateTime.now().plus(250, ChronoUnit.MILLIS);

            DefenseMatrixModel model = new DefenseMatrixModel(p.getLocation());

            if (defenseTask != null) {
                defenseTask.cancel();
            }
            defenseTask = new BukkitRunnable() {
                int ticksRan = 0;

                @Override
                public void run() {
                    model.setLoc(p.getLocation());
                    model.draw();

                    p.getLocation().getWorld().getEntitiesByClass(Projectile.class)
                            .stream()
                            .filter(proj -> proj.getLocation().distanceSquared(p.getLocation()) < 10 * 10)
                            .filter(proj -> model.isInside(proj.getLocation()))
                            .forEach(proj -> {
                                proj.getWorld().spawnParticle(Particle.SPELL_WITCH, proj.getLocation(), 5, 0, 0, 0, .5);
                                proj.getWorld().playSound(proj.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
                                proj.remove();
                                EntityProperties.remove(proj); // just in case
                            });

                    if (ticksRan >= 5) {
                        this.cancel();
                        defenseTask = null;
                    }
                    ticksRan++;
                }
            }.runTaskTimer(DevathonPlugin.getInstance(), 0L, 1L);

            // Start the regen task after a second of no defense matrix use
            if (regenTask != null) {
                regenTask.cancel();
            }
            regenTask = new BukkitRunnable() {
                @Override
                public void run() {
                    setUsage(Math.min(getUsage() + 1, 100), p);

                    if (getUsage() == 100) {
                        this.cancel();
                        regenTask = null;
                    }
                }
            }.runTaskTimer(DevathonPlugin.getInstance(), 20L, 2L);
        }
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage, Player toSendMessage) {
        this.usage = usage;
        if (toSendMessage != null) {

            // API is currently broken (srs, ask kenny about it) so I gotta do packets.
            // I wish I didn't have to, though, if that means anything to whoever reads this.

            PacketPlayOutChat packet = new PacketPlayOutChat(
                    IChatBaseComponent.ChatSerializer.b(
                            "§a" + new String(new char[usage]).replace("\0", "|") +
                                    "§c" + new String(new char[100 - usage]).replace("\0", "|")),
                    (byte) 2);
            ((CraftPlayer) toSendMessage).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
