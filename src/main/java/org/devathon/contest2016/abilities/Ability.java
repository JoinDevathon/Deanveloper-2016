package org.devathon.contest2016.abilities;

import org.bukkit.entity.Player;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dean
 */
public enum Ability {
    FUSION_CANNON(FusionCannon::new),
    DEFENSE_MATRIX(DefenseMatrix::new),
    BOOSTERS(Placeholder::new);

    private Function<Player, AbilityBase> ability;

    Ability(Function<Player, AbilityBase> ability) {
        this.ability = ability;
    }

    public AbilityBase getAbility(Player p) {
        return ability.apply(p);
    }
}
