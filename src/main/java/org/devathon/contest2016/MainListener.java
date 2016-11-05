package org.devathon.contest2016;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Dean
 */
public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new SteVA(e.getPlayer());
        e.getPlayer().addPotionEffect(
                new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2, true, false), true
        );
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        SteVA.getPlayers().remove(e.getPlayer().getUniqueId());
    }

}
