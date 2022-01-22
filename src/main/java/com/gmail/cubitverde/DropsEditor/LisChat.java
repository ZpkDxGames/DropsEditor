package com.gmail.cubitverde.DropsEditor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class LisChat implements Listener {
    @EventHandler
    private void setEvent (AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if ((!CustomDrops.settingChanceMob.keySet().contains(uuid) || !CustomDrops.settingChanceDrop.keySet().contains(uuid))
                && (!CustomDrops.addingCommandMob.keySet().contains(uuid) || !CustomDrops.addingCommandDrop.keySet().contains(uuid))) {
            return;
        }

        event.setCancelled(true);
        String message = event.getMessage();

        if (CustomDrops.settingChanceMob.keySet().contains(uuid)) {
            ObjDrop objDrop = CustomDrops.settingChanceDrop.get(uuid);
            ObjMob objMob = CustomDrops.settingChanceMob.get(uuid);
            try {
                double chance = Double.parseDouble(message);
                if (chance < 0 || chance > 1) {
                    throw new Exception();
                }
                objDrop.setChance(chance);

            } catch (Exception e) {
                player.sendMessage(ChatColor.DARK_RED + "Invalid number: " + ChatColor.RED + message);
                player.sendMessage(ChatColor.DARK_RED + "The drop chance must be given as " + ChatColor.RED + "a number between 0 and 1" + ChatColor.DARK_RED + ".");
            }
            Bukkit.getScheduler().runTask(CustomDrops.plugin, () -> {
                player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                CustomDrops.settingChanceMob.remove(uuid);
                CustomDrops.settingChanceDrop.remove(uuid);
            });

        } else if (CustomDrops.addingCommandMob.keySet().contains(uuid)) {
            ObjDrop objDrop = CustomDrops.addingCommandDrop.get(uuid);
            ObjMob objMob = CustomDrops.addingCommandMob.get(uuid);
            objDrop.getCommands().add(message.toLowerCase());
            player.sendMessage(ChatColor.DARK_GREEN + "Command added: " + ChatColor.GREEN + message.toLowerCase());
            Bukkit.getScheduler().runTask(CustomDrops.plugin, () -> {
                player.openInventory(UtiMenus.DropCommandsMenu(objMob, objDrop, 1));
                CustomDrops.addingCommandMob.remove(uuid);
                CustomDrops.addingCommandDrop.remove(uuid);
            });

        }
    }
}
