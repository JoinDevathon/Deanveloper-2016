package org.devathon.contest2016.misc;

import org.bukkit.util.Vector;

/**
 * @author Dean
 */
public class Utils {
    public static Vector rotateAroundY(Vector vec, double rad) {
        Vector result = vec.clone();
        double x = result.getX();
        double z = result.getZ();
        double sin = Math.sin(-rad);
        double cos = Math.cos(-rad);

        result.setX(z * sin + x * cos);
        result.setZ(z * cos - x * sin);

        return result;
    }
}
