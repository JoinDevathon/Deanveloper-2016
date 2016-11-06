package org.devathon.contest2016.model;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.misc.Utils;

import java.util.Arrays;

/**
 * @author Dean
 */
public class DefenseMatrixModel {
    private Location loc;
    private Vector[] parts = new Vector[8];
    private Location[] locations = new Location[8];

    public DefenseMatrixModel(Location loc) {
        this.loc = loc;

        constructModel();
    }

    private void constructModel() {
        // near endpoints
        parts[0] = new Vector(-2, .5, 1);
        parts[1] = new Vector(2, .5, 1);
        parts[2] = new Vector(-2, 3, 1);
        parts[3] = new Vector(2, 3, 1);

        // far endpoints
        parts[4] = new Vector(-3, 0, 7);
        parts[5] = new Vector(3, 0, 7);
        parts[6] = new Vector(-3, 5, 7);
        parts[7] = new Vector(3, 5, 7);
    }

    public Location getLoc() {
        return loc;
    }

    public void draw() {
        // near square
        drawBetween(0, 1);
        drawBetween(0, 2);
        drawBetween(1, 3);
        drawBetween(2, 3);

        // far square
        drawBetween(4, 5);
        drawBetween(4, 6);
        drawBetween(5, 7);
        drawBetween(6, 7);

        // connect squares
        drawBetween(0, 4);
        drawBetween(1, 5);
        drawBetween(2, 6);
        drawBetween(3, 7);
    }

    /**
     * Draws between two locations specified in the locations array.
     */
    private void drawBetween(int start, int end) {
        drawBetween(locations[start], locations[end].clone().subtract(locations[start]).toVector());
    }

    private void drawBetween(Location start, Vector until) {
        Vector step = until.clone().normalize().multiply(.2);
        Location mutatable = start.clone();
        do {
            start.getWorld().spawnParticle(Particle.CRIT_MAGIC, mutatable, 1, 0, 0, 0, 0);
            mutatable.add(step);
        } while (mutatable.distanceSquared(start) < until.lengthSquared());
    }

    public void setLoc(Location newLoc) {
        this.loc = newLoc.clone();
        this.loc.setPitch(newLoc.getPitch() - 20); // adjust for third person

        locations = Arrays.stream(parts)
                .map(v -> {
                    Vector afterRotation = Utils.rotateVector(
                            v,
                            Math.toRadians(-loc.getPitch()),
                            Math.toRadians(loc.getYaw()),
                            0
                    );
                    return loc.clone().add(afterRotation);
                }).toArray(Location[]::new);
    }

    // The math to check inside a rotated prism is really complicated, so instead,
    // let's do the math as if it's straight, and we adjust the location.
    public boolean isInside(Location other) {
        // Calculate where the vectors point to
        Vector relative = other.clone().subtract(loc).toVector();

        relative = Utils.rotateAroundY(relative, Math.toRadians(-(loc.getYaw())));
        relative = Utils.rotateAroundX(relative, Math.toRadians(loc.getPitch()));

        return isInsideX(relative) && isInsideY(relative) && isInsideZ(relative);
    }

    // -(2 + 1/7 * z) < locX < 2 + 1/7 * z
    private boolean isInsideX(Vector relative) {
        return -(2.0 + 1.0 / 7.0 * relative.getZ()) < relative.getY() &&
                relative.getX() < (2.0 + 1.0 / 7.0 * relative.getZ());
    }

    // .5 - .5 * z < locY < 3 + 2 * z
    private boolean isInsideY(Vector relative) {
        return .5 - .5 * relative.getZ() < relative.getY() &&
                relative.getX() < 3.0 + 2.0 * relative.getY();
    }

    // 1 < locZ < 7
    private boolean isInsideZ(Vector relative) {
        return 1 < relative.getZ() && relative.getZ() < 7;
    }
}
