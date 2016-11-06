package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.devathon.contest2016.abilities.Ability;
import org.devathon.contest2016.abilities.AbilityBase;
import org.devathon.contest2016.model.IdleModel;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Dean
 */
public class SteVa implements Listener {

    private final static Map<UUID, SteVa> players = new HashMap<>(4);

    private final IdleModel model;
    private final WeakReference<Player> player;
    private final UUID id;
    private final AbilityBase[] abilities;

    public SteVa(Player player) {
        this.player = new WeakReference<>(player);
        this.model = new IdleModel(player.getLocation());
        this.id = player.getUniqueId();

        players.put(player.getUniqueId(), this);
        Bukkit.getPluginManager().registerEvents(this, DevathonPlugin.getInstance());

        abilities = Arrays.stream(Ability.values())
                .map(ability -> ability.getAbility(player))
                .toArray(AbilityBase[]::new);
    }

    public static Map<UUID, SteVa> getPlayers() {
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

    public IdleModel getModel() {
        return model;
    }

    public void update() {
        model.setLoc(getPlayer().getLocation());
    }

    public void destroy() {
        players.remove(id);
        model.destroy();
    }

    public AbilityBase[] getAbilities() {
        return abilities;
    }
}
