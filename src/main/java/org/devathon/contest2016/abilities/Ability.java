package org.devathon.contest2016.abilities;

/**
 * @author Dean
 */
public enum Ability {
    FUSION_CANNON(new FusionCannon()),
    DEFENSE_MATRIX(new Placeholder()),
    BOOSTERS(new Placeholder());

    private AbilityBase ability;

    Ability(AbilityBase ability) {
        this.ability = ability;
    }

    public AbilityBase getAbility() {
        return ability;
    }
}
