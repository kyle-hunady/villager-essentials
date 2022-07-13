package me.kylehunady;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.bananapuncher714.nbteditor.NBTEditor.NBTCompound;

public class GossipHandler implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {

        // check if player is right clicking a villager or zombie villager
        Entity entity = event.getRightClicked();
        if (!(entity instanceof Villager || entity instanceof ZombieVillager))
            return;

        // check if the villager has a Gossips property to parse
        if (!(NBTEditor.contains(entity, "Gossips", 0)))
            return;

        // the following native MC command (cheats enabled) will grab the Gossips data
        // of the nearest villager
        // /data get entity @e[type=villager, sort=nearest, limit=1] Gossips

        // a villager's "Gossips" property may look like this:
        // [{Target:[I;xxxx,xxxx,xxxx,xxxx],Type:"major_positive",Value:20},
        // {Target:[I;yyyy,yyyy,yyyy,yyyy],Type:"minor_negative",Value:50},
        // {Target:[I;xxxx,xxxx,xxxx,xxxx],Type:"minor_negative",Value:25}]

        // Target is an integer array (the "I;" is for show) representing a player's
        // unique user ID (UUID)
        // Type represents the gossip for that player (major_positive, minor_positive,
        // major_negative, minor_negative, trade)
        // Value represents the weight of that gossip

        // Note: Gossips property is a list. Each element is a compound tag containing
        // Target, Type, and Value
        NBTCompound gossips = NBTEditor.getNBTCompound(entity, "Gossips");
        int size = NBTEditor.getSize(gossips);

        Player player = event.getPlayer();
        int[] playerId = NBTEditor.getIntArray(player, "UUID");

        int maxValue = 0;
        // index is needed to change the player's existing entry in Gossips property
        int playerIndex = -1;

        String[] gossipTypes = { "minor_positive", "major_positive" };
        for (String type : gossipTypes) {
            for (int i = 0; i < size; i++) {
                // grab the ith element in Gossips and check its Type
                if (NBTEditor.getString(gossips, i, "Type").equals(type)) {
                    int val = NBTEditor.getInt(gossips, i, "Value");
                    maxValue = (val > maxValue) ? val : maxValue;

                    // determine if the player already has a Gossips entry and save index
                    if (Arrays.equals(playerId, NBTEditor.getIntArray(gossips, i, "Target")))
                        playerIndex = i;
                }

            }
            if (maxValue <= 0)
                return; // if no positive gossip exists, we don't need to update the villager data

            NBTCompound newGossipEntry = NBTEditor.getEmptyNBTCompound();
            newGossipEntry.set(playerId, "Target");
            newGossipEntry.set(type, "Type");
            newGossipEntry.set(maxValue, "Value");

            // this function can be a little backwards to read
            // take our villager and save the new entry to Gossips[index]
            if (playerIndex >= 0) {
                NBTEditor.set(entity, newGossipEntry, "Gossips", playerIndex);
            } else {
                NBTEditor.set(entity, newGossipEntry, "Gossips", NBTEditor.NEW_ELEMENT);
            }
        }
    }

    // EVERYTHING BELOW IS FOR DEBUGGING
    // @EventHandler
    // public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    //     if (!(event.getEntity() instanceof Villager))
    //         return;
    //     if (!(event.getDamager() instanceof Player))
    //         return;

    //     Villager entity = (Villager) event.getEntity();
    //     // check if the villager has a Gossips property to parse
    //     if (!(NBTEditor.contains(entity, "Gossips", 0)))
    //         return;
    //     NBTCompound tag = NBTEditor.getEmptyNBTCompound();
    //     // int[] playerId = { -1848488881, -494907661, -1780731098, 665058548 };
    //     Random rand = new Random();
    //     int value = rand.nextInt(0, 100);
    //     tag.set("major_positive", "Type");
    //     tag.set(value, "Value");
    //     tag.set(new int[] { 1, 1, 1, 1 }, "Target");
    //     NBTEditor.set(entity, tag, "Gossips", 0); // 0 means added to list at index 0
    //     tag.set("minor_positive", "Type");
    //     NBTEditor.set(entity, tag, "Gossips", 0);
    // }

    // private void printVal(String key, String val) {
    //     Bukkit.broadcastMessage("========================");
    //     Bukkit.broadcastMessage(key + ":" + val);
    // }

    // private void print(String message) {
    //     Bukkit.broadcastMessage(message);
    // }
}