package org.devathon.contest2016.misc;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.bukkit.entity.Entity;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Dean
 */
public class EntityProperties {
    private final static Multimap<UUID, String> properties = MultimapBuilder.hashKeys().arrayListValues().build();

    public static void addProperty(UUID id, String s) {
        properties.put(id, s);
    }

    public static Collection<String> getProperty(UUID id) {
        return properties.get(id);
    }

    public static boolean hasProperty(UUID id, String s) {
        return getProperty(id).contains(s);
    }

    public static void remove(UUID id) {
        properties.removeAll(id);
    }

    public static void addProperty(Entity e, String s) {
        properties.put(e.getUniqueId(), s);
    }

    public static Collection<String> getProperty(Entity e) {
        return properties.get(e.getUniqueId());
    }

    public static boolean hasProperty(Entity e, String s) {
        return getProperty(e.getUniqueId()).contains(s);
    }

    public static void remove(Entity e) {
        properties.removeAll(e.getUniqueId());
    }

    public static Multimap<UUID, String> getProperties() {
        return properties;
    }
}
