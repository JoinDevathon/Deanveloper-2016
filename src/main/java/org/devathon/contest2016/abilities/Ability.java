package org.devathon.contest2016.abilities;

import org.bukkit.inventory.ItemStack;

/**
 * @author Dean
 */
public enum Ability {
    FUSION_CANNON(new FusionCannon()),
    DEFENSE_MATRIX(),
    BOOSTERS();

    private AbilityBase ability;

    Ability(AbilityBase ability) {
        this.ability = ability;
    }

    public AbilityBase getAbility() {
        return ability;
    }
}
