package me.kylehunady;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;

public class Helper {
    private static final ArrayList<EntityType> zombieTypes = new ArrayList<EntityType>(){
        {
            add(EntityType.ZOMBIE);
            add(EntityType.ZOMBIE_VILLAGER);
            add(EntityType.DROWNED);
            add(EntityType.HUSK);
            // add(EntityType.ZOMBIFIED_PIGLIN); // nonaggressive towards villagers, not added
        }
    };

    public static boolean isZombieType(EntityType entityType) {
        return zombieTypes.contains(entityType);
    }
    
}
