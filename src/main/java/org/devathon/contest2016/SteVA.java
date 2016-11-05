package org.devathon.contest2016;

import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Dean
 */
public class SteVA implements Listener {
    //region Initialization

    private final static Map<UUID, SteVA> players = new HashMap<>(4);

    private final WeakReference<Player> player;

    public SteVA(Player player) {
        this.player = new WeakReference<>(player);

        Bukkit.getPluginManager().registerEvents(this, DevathonPlugin.getInstance());
    }

    public static Map<UUID, SteVA> getPlayers() {
        return players;
    }

    @NotNull
    public Player getPlayer() {
        Player p = player.get();
        if (p != null) {
            return p;
        } else {
            throw new NullPointerException();
        }
    }

    //endregion

    //region Visual

    public void create() {

    }
    //endregion
}
