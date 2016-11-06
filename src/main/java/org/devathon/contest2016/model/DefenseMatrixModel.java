package org.devathon.contest2016.model;

import org.bukkit.Location;
import org.bukkit.util.Vector;
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

    public void setLoc(Location loc) {
        this.loc = loc;
        locations = Arrays.stream(parts)
                .map(v -> {
                    Vector afterRotation = Utils.rotateAroundY(v, Math.toRadians(loc.getYaw()));
                    return loc.clone().add(afterRotation);
                }).toArray(Location[]::new);
    }

    // The math to check inside a rotated prism is really complicated, so instead,
    // let's do the math as if it's straight, and we adjust the location.
    public boolean isInside(Location other) {
        // Calculate where the vectors point to
        Vector relative = other.clone().subtract(loc).toVector();
        Location temp = loc.clone();
        temp.setPitch(0);

        relative = Utils.rotateAroundY(relative, loc.getDirection().angle(new Vector(1, 0, 0)));

        return isInsideX(relative) && isInsideY(relative) && isInsideZ(relative);
    }

    // -(2 + 1/7 * z) < locX < 2 + 1/7 * z
    private boolean isInsideX(Vector relative) {
        return -(3.0 + 1.0/7.0 * relative.getZ()) < relative.getY() &&
                relative.getX() < (3.0 + 1.0/7.0 * relative.getZ());
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
