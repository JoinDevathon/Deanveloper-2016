package org.devathon.contest2016;

import org.bukkit.DyeColor;
import org.bukkit.util.Vector;

/**
 * @author Dean
 */
public class Model {
    

    public class ModelPart {
        private final DyeColor dye;
        private final Vector l;

        public ModelPart(DyeColor dye, Vector l) {
            this.dye = dye;
            this.l = l;
        }
    }
}
