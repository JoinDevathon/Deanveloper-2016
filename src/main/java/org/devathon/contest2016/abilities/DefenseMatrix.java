package org.devathon.contest2016.abilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
import java.util.UUID;

/**
 * @author Dean
 */
public class DefenseMatrix implements AbilityBase {
    @SuppressWarnings("deprecation")
    private final ItemStack item = new ItemStack(Material.STAINED_GLASS, 1, DyeColor.BLUE.getWoolData());
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
            setUsage(getUsage() - 16, p);
            cooldown = LocalDateTime.now().plus(250, ChronoUnit.MILLIS);

            DefenseMatrixModel model = new DefenseMatrixModel(p.getLocation());

            if (defenseTask != null) {
                defenseTask.cancel();
            }
            defenseTask = new BukkitRunnable() {
                boolean ranAlready = false;

                @Override
                public void run() {
                    model.setLoc(p.getLocation());
                    model.draw();

                    p.getLocation().getWorld().getEntitiesByClass(Projectile.class)
                            .stream()
                            .filter(proj -> proj.getLocation().distanceSquared(p.getLocation()) < 10 * 10)
                            .filter(proj -> model.isInside(proj.getLocation()))
                            .forEach(proj -> {
                                proj.getWorld().spawnParticle(Particle.SPELL_WITCH, proj.getLocation(), 1);
                                proj.getWorld().playSound(proj.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
                                proj.remove();
                                EntityProperties.remove(proj); // just in case
                            });

                    if (ranAlready) {
                        this.cancel();
                        defenseTask = null;
                    }
                    ranAlready = true;
                }
            }.runTaskTimer(DevathonPlugin.getInstance(), 0L, 1L);

            // Start the regen task after a second of no defense matrix use
            if (regenTask != null) {
                regenTask.cancel();
            }
            regenTask = new BukkitRunnable() {
                @Override
                public void run() {
                    setUsage(Math.min(getUsage() + 10, 100), p);

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

        String text = "§d" + new String(new char[usage]).replace("\0", "|");
        text += "§7" + new String(new char[100 - usage]).replace("\0", "|");
        if (toSendMessage != null) {
            toSendMessage.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text));
        }
    }
}
