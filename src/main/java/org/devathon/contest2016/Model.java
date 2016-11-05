package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dean
 */
public class Model {
    private static final Vector OFFSET = new Vector(0, -1.2, 0);
    private Location loc;
    private final List<ModelPart> parts = new ArrayList<>();

    private static final ItemStack SKULL;

    static {
        //noinspection deprecation
        SKULL = Bukkit.getUnsafe().modifyItemStack(new ItemStack(Material.SKULL_ITEM, 1, (short) 3),
                "{display:{Name:\"DVa\"}," +
                        "SkullOwner:{Id:\"d8575558-fb10-4e97-b346-696541112f84\"," +
                        "Properties:{textures:[{" +
                        "Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3Jh" +
                        "ZnQubmV0L3RleHR1cmUvZTI3NDkzNjhmZTgxYTZmZDA0MDI1N2UzOWIwMjQ3N2QyZGY0OTg5ZDkzM" +
                        "WViNjUyNTI0MWRmOTY1MTkxMWYwIn19fQ==\"" +
                        "}]}}}");
    }

    @SuppressWarnings("deprecation")
    public Model(Location loc) {
        this.loc = loc;
        populateModel();
    }

    @SuppressWarnings("deprecation")
    private void populateModel() {
        // legs
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(.4, .5, 0)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-.4, .5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.4, .925, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.4, .925, 0)));
        parts.add(new ModelPart(DyeColor.CYAN, new Vector(0, 1.15, 0)));

        // body back
        // mid
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.75, 1.85, -.2)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 1.85, -.2)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(.5, 1.85, -.37)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-.5, 1.85, -.37)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(.25, 1.85, -.45)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-.25, 1.85, -.45)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.85, -.5)));
        // top
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.75, 2.2, -.15)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 2.2, -.15)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(.5, 2.2, -.3)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-.5, 2.2, -.3)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(.25, 2.2, -.4)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-.25, 2.2, -.4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.2, -.45)));
        // bottom
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(.5, 1.5, -.3)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-.5, 1.5, -.3)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(.25, 1.5, -.4)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-.25, 1.5, -.4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.5, -.45)));

        // body front
        // mid
        ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIME.getWoolData());
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.75, 1.85, .2)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 1.85, .2)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 1.85, .37)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 1.85, .37)));
        // top
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.75, 2.2, .15)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 2.2, .15)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 2.2, .3)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 2.2, .3)));
        // bottom
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.5, 1.5, .3)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 1.5, .3)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.25, 1.5, .4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.25, 1.5, .4)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.5, .45)));


        // tippy top
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.7, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(.4, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.5, -.2)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.4, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-.7, 2.5, 0)));

        // glass on front
        // mid
        parts.add(new ModelPart(stack, new Vector(.5, 1.53, .75), new EulerAngle(0, -Math.PI / 4, 0)));
        parts.add(new ModelPart(stack, new Vector(-.5, 1.53, .75), new EulerAngle(0, Math.PI / 4, 0)));
        parts.add(new ModelPart(stack, new Vector(0, 1.53, .96)));
        // top
        parts.add(new ModelPart(stack, new Vector(.5, 1.88, .75), new EulerAngle(0, -Math.PI / 4, 0)));
        parts.add(new ModelPart(stack, new Vector(-.5, 1.88, .75), new EulerAngle(0, Math.PI / 4, 0)));
        parts.add(new ModelPart(stack, new Vector(0, 1.88, .96)));

        // tippy top glass
        parts.add(new ModelPart(stack, new Vector(0, 2.5, 1.1), new EulerAngle(-Math.PI / 4, 0, 0)));
        parts.add(new ModelPart(stack, new Vector(.65, 2.5, .85), new EulerAngle(-Math.PI / 4, -Math.PI / 4, 0)));
        parts.add(new ModelPart(stack, new Vector(-.65, 2.5, .85), new EulerAngle(-Math.PI / 4, Math.PI / 4, 0)));

        // shoulders
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(1, 2.4, 0)));
        parts.add(new ModelPart(DyeColor.WHITE, new Vector(-1, 2.4, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1.3, 2.3, -.1)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1.3, 2.3, -.1)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1.3, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1.3, 2.5, 0)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(1.3, 2.5, -.1)));
        parts.add(new ModelPart(DyeColor.PINK, new Vector(-1.3, 2.5, -.1)));

        // arms
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                parts.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, 0)));
                parts.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, 0)));
                parts.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, .3)));
                parts.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, .3)));
                parts.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, .6)));
                parts.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, .6)));
                parts.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, .8)));
                parts.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, .8)));
            }
        }

        parts.add(new ModelPart(SKULL, new Vector(0, 2.1, -.35)));
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

        private ModelPart(ItemStack helm, Vector relativeLoc, EulerAngle headPose) {
            this.relativeLoc = relativeLoc.add(OFFSET);
            this.stand = DevathonPlugin.getMainWorld().spawn(loc.clone().add(relativeLoc), ArmorStand.class);
            stand.setVisible(false);
            stand.setSmall(true);
            stand.setHelmet(helm);
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setHeadPose(headPose);
        }

        private ModelPart(ItemStack helm, Vector relativeLoc) {
            this(helm, relativeLoc, EulerAngle.ZERO);
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
