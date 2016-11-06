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

    public static Vector rotateAroundX(Vector vec, double rad) {
        Vector result = vec.clone();
        double y = result.getY();
        double z = result.getZ();
        double sin = Math.sin(-rad);
        double cos = Math.cos(-rad);
        result.setY(y * cos - z * sin);
        result.setZ(y * sin + z * cos);
        return result;
    }

    public static Vector rotateAroundZ(Vector vec, double rad) {
        Vector result = vec.clone();
        double x = result.getX();
        double y = result.getY();
        double sin = Math.sin(-rad);
        double cos = Math.cos(-rad);
        result.setX(x * cos - y * sin);
        result.setY(x * sin + y * cos);
        return result;
    }

    public static final Vector rotateVector(Vector v, double radX, double radY, double radZ) {
        Vector result = v.clone();
        result = rotateAroundX(result, radX);
        result = rotateAroundY(result, radY);
        result = rotateAroundZ(result, radZ);
        return result;
    }
}
