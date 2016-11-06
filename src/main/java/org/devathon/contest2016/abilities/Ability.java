package org.devathon.contest2016.abilities;

import java.util.function.Supplier;

/**
 * @author Dean
 */
public enum Ability {
    FUSION_CANNON(FusionCannon::new),
    DEFENSE_MATRIX(DefenseMatrix::new),
    BOOSTERS(Booster::new);

    private Supplier<AbilityBase> ability;

    Ability(Supplier<AbilityBase> ability) {
        this.ability = ability;
    }

    public AbilityBase getAbility() {
        return ability.get();
    }
}
