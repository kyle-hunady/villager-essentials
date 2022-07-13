package me.kylehunady;

import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ZombificationHandler implements Listener {

    /** @noinspection unused */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity deadEntity = event.getEntity();

        // Check if dead entity is a villager
        if (!(deadEntity instanceof Villager)) return;
        Villager villagerEnt = (Villager) deadEntity;

        EntityDamageEvent deathEvent = villagerEnt.getLastDamageCause();

        // Check if death was caused by another entity
        if (!(deathEvent.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)) return;
        Entity killerEnt = ((EntityDamageByEntityEvent) deathEvent).getDamager();

        // Check if killer is a zombie variant
        if (!(Helper.isZombieType(killerEnt.getType()))) return;

        villagerEnt.zombify();
    }
}