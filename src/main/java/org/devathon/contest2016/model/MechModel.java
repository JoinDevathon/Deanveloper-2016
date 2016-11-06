package org.devathon.contest2016.model;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.misc.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dean
 */
public class MechModel {
    private static final ItemStack SKULL;
    private static final Vector OFFSET = new Vector(0, -1.2, 0);

    private List<ModelPart> parts = new ArrayList<>();
    private List<ModelPart> legs = new ArrayList<>();
    private List<ModelPart> body = new ArrayList<>();
    private List<ModelPart> arms = new ArrayList<>();
    private final ModelPart leftHand;
    private final ModelPart rightHand;

    private Location loc;

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

    public MechModel(Location loc) {
        this.loc = loc;

        construct();

        parts.addAll(legs);
        parts.addAll(body);
        parts.addAll(arms);

        setLoc(loc);

        leftHand = arms.get(arms.size() - 1);
        rightHand = arms.get(arms.size() - 2);

        parts.add(new ModelPart(SKULL, new Vector(0, 2.1, -.35)));
    }

    public Location leftHandLoc() {
        return leftHand.stand.getEyeLocation();
    }

    public Location rightHandLoc() {
        return rightHand.stand.getEyeLocation();
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        loc = loc.clone();
        this.loc = loc;

        for (ModelPart part : parts) {
            Vector afterRotation = Utils.rotateAroundY(part.relativeLoc, Math.toRadians(loc.getYaw()));
            Location partLoc = loc.clone().add(afterRotation);
            part.stand.teleport(partLoc);
        }
    }

    public void destroy() {
        loc = null;
        parts.forEach(ModelPart::destroy);
    }

    public void construct() {

        // I would use loops to add all of these, except specifying each one individually
        // gives me the ability to adjust things like color, angles, and oval-like shapes

        // legs
        legs.add(new ModelPart(DyeColor.WHITE, new Vector(.4, .5, 0)));
        legs.add(new ModelPart(DyeColor.WHITE, new Vector(-.4, .5, 0)));
        legs.add(new ModelPart(DyeColor.PINK, new Vector(.4, .925, 0)));
        legs.add(new ModelPart(DyeColor.PINK, new Vector(-.4, .925, 0)));
        legs.add(new ModelPart(DyeColor.CYAN, new Vector(0, 1.15, 0)));

        // body back
        // mid
        body.add(new ModelPart(DyeColor.PINK, new Vector(.75, 1.85, -.2)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 1.85, -.2)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(.5, 1.85, -.37)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(-.5, 1.85, -.37)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(.25, 1.85, -.45)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(-.25, 1.85, -.45)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.85, -.5)));
        // top
        body.add(new ModelPart(DyeColor.PINK, new Vector(.75, 2.2, -.15)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 2.2, -.15)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(.5, 2.2, -.3)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(-.5, 2.2, -.3)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(.25, 2.2, -.4)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(-.25, 2.2, -.4)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.2, -.45)));
        // bottom
        body.add(new ModelPart(DyeColor.WHITE, new Vector(.5, 1.5, -.3)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(-.5, 1.5, -.3)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(.25, 1.5, -.4)));
        body.add(new ModelPart(DyeColor.WHITE, new Vector(-.25, 1.5, -.4)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.5, -.45)));

        // body front
        // mid
        ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIME.getWoolData());
        body.add(new ModelPart(DyeColor.PINK, new Vector(.75, 1.85, .2)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 1.85, .2)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(.5, 1.85, .37)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 1.85, .37)));
        // top
        body.add(new ModelPart(DyeColor.PINK, new Vector(.75, 2.2, .15)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.75, 2.2, .15)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(.5, 2.2, .3)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 2.2, .3)));
        // bottom
        body.add(new ModelPart(DyeColor.PINK, new Vector(.5, 1.5, .3)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.5, 1.5, .3)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(.25, 1.5, .4)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.25, 1.5, .4)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(0, 1.5, .45)));


        // tippy top
        body.add(new ModelPart(DyeColor.PINK, new Vector(.7, 2.5, 0)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(.4, 2.5, 0)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.5, 0)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(0, 2.5, -.2)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.4, 2.5, 0)));
        body.add(new ModelPart(DyeColor.PINK, new Vector(-.7, 2.5, 0)));

        // glass on front
        // mid
        body.add(new ModelPart(stack, new Vector(.5, 1.53, .75), new EulerAngle(0, -Math.PI / 4, 0)));
        body.add(new ModelPart(stack, new Vector(-.5, 1.53, .75), new EulerAngle(0, Math.PI / 4, 0)));
        body.add(new ModelPart(stack, new Vector(0, 1.53, .96)));
        // top
        body.add(new ModelPart(stack, new Vector(.5, 1.88, .75), new EulerAngle(0, -Math.PI / 4, 0)));
        body.add(new ModelPart(stack, new Vector(-.5, 1.88, .75), new EulerAngle(0, Math.PI / 4, 0)));
        body.add(new ModelPart(stack, new Vector(0, 1.88, .96)));

        // tippy top glass
        body.add(new ModelPart(stack, new Vector(0, 2.5, 1.1), new EulerAngle(-Math.PI / 4, 0, 0)));
        body.add(new ModelPart(stack, new Vector(.65, 2.5, .85), new EulerAngle(-Math.PI / 4, -Math.PI / 4, 0)));
        body.add(new ModelPart(stack, new Vector(-.65, 2.5, .85), new EulerAngle(-Math.PI / 4, Math.PI / 4, 0)));

        // shoulders
        arms.add(new ModelPart(DyeColor.WHITE, new Vector(1, 2.4, 0)));
        arms.add(new ModelPart(DyeColor.WHITE, new Vector(-1, 2.4, 0)));
        arms.add(new ModelPart(DyeColor.PINK, new Vector(1.3, 2.3, -.1)));
        arms.add(new ModelPart(DyeColor.PINK, new Vector(-1.3, 2.3, -.1)));
        arms.add(new ModelPart(DyeColor.PINK, new Vector(1.3, 2.5, 0)));
        arms.add(new ModelPart(DyeColor.PINK, new Vector(-1.3, 2.5, 0)));
        arms.add(new ModelPart(DyeColor.PINK, new Vector(1.3, 2.5, -.1)));
        arms.add(new ModelPart(DyeColor.PINK, new Vector(-1.3, 2.5, -.1)));

        // arms
        // this loop makes the arms nice and thick by using 4 armorstands per unit outward
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                arms.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, 0)));
                arms.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, 0)));
                arms.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, .3)));
                arms.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, .3)));
                arms.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, .6)));
                arms.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, .6)));
                arms.add(new ModelPart(DyeColor.PINK, new Vector(1.3 + i * .1, 2 - j * .1, .8)));
                arms.add(new ModelPart(DyeColor.PINK, new Vector(-1.3 - i * .1, 2 - j * .1, .8)));
            }
        }
    }



    protected class ModelPart {

        private final Vector relativeLoc;
        private final ArmorStand stand;

        protected ModelPart(ItemStack helm, Vector relativeLoc, EulerAngle headPose) {
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
}
