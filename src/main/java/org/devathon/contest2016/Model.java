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
    private static final Vector OFFSET = new Vector(0, -1.5, 0);
    private Location loc;
    private final List<ModelPart> parts = new ArrayList<>();

    public Model(Location loc) {
        this.loc = loc;
        // legs
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1, .5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1, .5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 1, 0)));


    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        loc = loc.clone();
        this.loc = loc;

        for (ModelPart part : parts) {
            Vector afterRotation = rotateAroundY(part.relativeLoc, Math.toRadians(loc.getYaw()));
            Location partLoc = loc.clone().add(afterRotation);
            part.stand.teleport(partLoc);
        }
    }

    private Vector rotateAroundY(Vector vec, double rad) {
        Vector result = vec.clone();
        double x = result.getX();
        double z = result.getZ();
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);

        result.setX(z * sin + x * cos);
        result.setZ(z * cos + x * sin);

        return result;
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
            stand.setInvulnerable(true);
        }
    }
}
