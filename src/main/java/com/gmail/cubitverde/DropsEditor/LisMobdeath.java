package com.gmail.cubitverde.DropsEditor;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.LinkedList;
import java.util.List;

public class LisMobdeath implements Listener {
    @EventHandler
    private void setEvent (EntityDeathEvent event) {
        EntityType entityType = event.getEntityType();
        LivingEntity entity = event.getEntity();
        List<ItemStack> drops = event.getDrops();
        Player killer = entity.getKiller();
        Location location = entity.getLocation();

        ObjMob mob = null;
        for (ObjMob tempMob : CustomDrops.editedMobs) {
            if (tempMob.getType().equals(entityType)) {
                mob = tempMob;
                break;
            }
        }
        if (mob == null) {
            return;
        }

        if (mob.getInactiveWorlds().contains(entity.getWorld().toString().toLowerCase())) {
            return;
        }

        if (!mob.getVanillaDrops()) {
            drops.clear();
        }

        LinkedList<ObjDrop> mobDrops = mob.getDrops();
        for (ObjDrop objDrop : mobDrops) {
            double rand = Math.random();
            if (rand >= objDrop.getChance()) {
                continue;
            }

            if (!objDrop.getSpawnerDrops() && CustomDrops.spawnerMobs.contains(entity.getUniqueId())) {
                continue;
            } else if (!objDrop.getEggDrops() && CustomDrops.spawnereggMobs.contains(entity.getUniqueId())) {
                continue;
            }

            // OTHER CONDITIONS

            if (!objDrop.getItem().getType().equals(Material.BARRIER)) {
                drops.add(objDrop.getItem());
            }

            if (objDrop.getEffect()) {
                Firework firework = (Firework) entity.getLocation().getWorld().spawnEntity(location, EntityType.FIREWORK);
                FireworkMeta fireworkMeta = firework.getFireworkMeta();
                fireworkMeta.setPower(0);
                fireworkMeta.addEffect(FireworkEffect.builder().withColor(Utilities.GetColorFromString(objDrop.getColor())).with(objDrop.getShape()).build());
                firework.setFireworkMeta(fireworkMeta);
                firework.detonate();
                CustomDrops.dropFireworks.add(firework);
            }

            for (String command : objDrop.getCommands()) {
                CommandSender sender = CustomDrops.plugin.getServer().getConsoleSender();

                List<String> runCommands = Utilities.readCommandPlaceholders(command, killer, location);
                if (runCommands == null) {
                    continue;
                }
                for (String runCommand : runCommands) {
                    CustomDrops.plugin.getServer().dispatchCommand(sender, runCommand);
                }
            }

        }
    }
}
