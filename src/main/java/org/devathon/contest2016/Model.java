package org.devathon.contest2016;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dean
 */
public class Model {
    private static final Vector OFFSET = new Vector(0, -1, 0);
    private Location loc;
    private final List<ModelPart> parts = new ArrayList<>();

    public Model(Location loc) {
        this.loc = loc;
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1, 0, 0)));
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    private class ModelPart {

        private final Vector relativeLoc;
        private final ArmorStand stand;

        private ModelPart(DyeColor dye, Vector relativeLoc) {
            this.relativeLoc = relativeLoc.add(OFFSET);
            this.stand = DevathonPlugin.getMainWorld().spawn(loc.clone().add(relativeLoc), ArmorStand.class);
            stand.setVisible(false);
            stand.setSmall(false);
            stand.setHelmet(new ItemStack(Material.STAINED_CLAY, 1, dye.getWoolData()));
            stand.setGravity(false);
        }
    }
}
