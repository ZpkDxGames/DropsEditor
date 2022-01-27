package com.gmail.cubitverde.DropsEditor;

import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LisDamaged implements Listener {
    @EventHandler
    private void setEvent (EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Firework && event.getEntity() instanceof Player) {
            Firework firework = (Firework) event.getDamager();
            if (CustomDrops.dropFireworks.contains(firework)) {
                event.setCancelled(true);
            }
        }
    }
}
