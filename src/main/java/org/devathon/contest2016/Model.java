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

    @SuppressWarnings("deprecation")
    public Model(Location loc) {
        this.loc = loc;
        // feet
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.6, .5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.6, .5, 0)));

        // legs
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.8, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.8, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.5, -.2)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.4, 1, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.4, 1, 0)));

        // body back (bottom)
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1, 1.5, -.25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1, 1.5, -.25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 1.5, -.4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 1.5, -.4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.5, -.5)));
        // body back (top)
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1, 2, -.25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1, 2, -.25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 2, -.4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 2, -.4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 2, -.5)));

        // body front (bottom)
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1, 1.5, .25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1, 1.5, .25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 1.5, .4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 1.5, .4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.5, .5)));
        // body front (top)
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1, 2, .25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1, 2, .25)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 2, .4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 2, .4)));
        parts.add(new ModelPart(new ItemStack(Material.STAINED_GLASS, 1, DyeColor.LIME.getWoolData()),
                new Vector(0, 2, .5)));

        // top
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.8, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.4, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.5, -.2)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.8, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.4, 2.5, 0)));
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
        double sin = Math.sin(-rad);
        double cos = Math.cos(-rad);

        result.setX(z * sin + x * cos);
        result.setZ(z * cos - x * sin);

        return result;
    }

    private class ModelPart {

        private final Vector relativeLoc;
        private final ArmorStand stand;

        private ModelPart(ItemStack helm, Vector relativeLoc) {
            this.relativeLoc = relativeLoc.add(OFFSET);
            this.stand = DevathonPlugin.getMainWorld().spawn(loc.clone().add(relativeLoc), ArmorStand.class);
            stand.setVisible(false);
            stand.setSmall(false);
            stand.setHelmet(helm);
            stand.setGravity(false);
            stand.setInvulnerable(true);
        }

        private ModelPart(DyeColor dye, Vector relativeLoc) {
            //noinspection deprecation
            this(new ItemStack(Material.STAINED_CLAY, 1, dye.getWoolData()), relativeLoc);
        }

        public void destroy() {
            stand.remove();
        }
    }

    public void destroy() {
        loc = null;
        parts.forEach(ModelPart::destroy);
    }
}
