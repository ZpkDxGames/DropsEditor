package com.gmail.cubitverde.DropsEditor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class LisMobspawn implements Listener {
    @EventHandler
    private void setEvent(CreatureSpawnEvent event) {
        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER)) {
            CustomDrops.spawnerMobs.add(event.getEntity().getUniqueId());
        } else if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
            CustomDrops.spawnereggMobs.add(event.getEntity().getUniqueId());
        }
    }
}
