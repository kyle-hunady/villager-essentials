package me.kylehunady;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import io.github.bananapuncher714.nbteditor.NBTEditor;

public class ZombieLootHandler implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();

        // Check if entity is a zombie variant
        if (!(Helper.isZombieType(entity.getType()))) return;

        Random rand = new Random();
        // generate number 0 or 1 (50% chance)
        // origin is inclusive, bound is exclusive
        boolean canPickUp = rand.nextInt(0,2) == 1;
        
        NBTEditor.set(entity,canPickUp,"CanPickUpLoot");
    }
}