package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Dean
 */
public class SteVA implements Listener {

    private final static Map<UUID, SteVA> players = new HashMap<>(4);

    private final IdleModel model;
    private final WeakReference<Player> player;
    private final UUID id;

    public SteVA(Player player) {
        this.player = new WeakReference<>(player);
        this.model = new IdleModel(player.getLocation());
        this.id = player.getUniqueId();

        players.put(player.getUniqueId(), this);
        Bukkit.getPluginManager().registerEvents(this, DevathonPlugin.getInstance());
    }

    public static Map<UUID, SteVA> getPlayers() {
        return players;
    }

    public Player getPlayer() {
        Player p = player.get();
        if (p != null) {
            return p;
        } else {
            throw new NullPointerException();
        }
    }

    public void update() {
        model.setLoc(getPlayer().getLocation());
    }

    public void destroy() {
        players.remove(id);
        model.destroy();
    }
}
