package com.gmail.cubitverde.DropsEditor;

import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

public class LisInventory implements Listener {
    @EventHandler
    private void setEvent (InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        InventoryView inventoryView = event.getView();
        if (inventory == null || !inventoryView.getTitle().startsWith(ChatColor.DARK_GREEN + "[CustomDrops]")) {
            return;
        }

        event.setCancelled(true);
        if (inventory.getType().equals(InventoryType.PLAYER)) {
            return;
        }

        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!player.hasPermission("dropseditor.admin")) {
            player.closeInventory();
            return;
        }

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getAmount() == 0) {
            return;
        }
        ItemMeta clickedMeta = clickedItem.getItemMeta();
        if (clickedMeta == null || clickedMeta.getDisplayName().equals(" ") || clickedMeta.getDisplayName().equals(ChatColor.GREEN + "[Drops Editor]")) {
            return;
        }

        switch (inventoryView.getTitle().substring(16)) {
            case "Main Menu": {
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Mob drops": {
                        player.openInventory(UtiMenus.MobsMenu(1));
                        return;
                    }
                    case "Block drops": {

                    }
                    case "Settings": {

                    }
                }
            }

            case "Mob selection": {
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.MainMenu());
                        return;
                    }
                    case "Next page": {
                        player.openInventory(UtiMenus.MobsMenu(Utilities.GetPage(inventory) + 1));
                        return;
                    }
                    case "Previous page": {
                        player.openInventory(UtiMenus.MobsMenu(Utilities.GetPage(inventory) - 1));
                        return;
                    }
                    default: {
                        String mobName = clickedMeta.getDisplayName().substring(2);
                        if (CustomDrops.mobIcons.keySet().contains(mobName)) {
                            ObjMob objMob = Utilities.GetObjMob(mobName);
                            player.openInventory(UtiMenus.MobSettingsMenu(objMob));
                        }
                        return;
                    }
                }
            }

            case "Mob settings": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.MobsMenu(1));
                        return;
                    }
                    case "Toggle vanilla drops": {
                        if (objMob.getVanillaDrops()) {
                            objMob.setVanillaDrops(false);
                        } else {
                            objMob.setVanillaDrops(true);
                        }
                        player.openInventory(UtiMenus.MobSettingsMenu(objMob));
                        return;
                    }
                    case "Toggle active worlds": {
                        player.openInventory(UtiMenus.MobWorldsMenu(objMob, 1));
                        return;
                    }
                    case "Edit custom drops": {
                        player.openInventory(UtiMenus.MobDropsMenu(objMob, 1));
                        return;
                    }
                }
            }

            case "Mob worlds": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.MobSettingsMenu(objMob));
                        return;
                    }
                    case "Next page": {
                        player.openInventory(UtiMenus.MobWorldsMenu(objMob, Utilities.GetPage(inventory) + 1));
                        return;
                    }
                    case "Previous page": {
                        player.openInventory(UtiMenus.MobWorldsMenu(objMob, Utilities.GetPage(inventory) - 1));
                        return;
                    }
                    default: {
                        String worldName = clickedMeta.getDisplayName().substring(2).toLowerCase();
                        World world = CustomDrops.plugin.getServer().getWorld(worldName);
                        if (world == null) {
                            return;
                        }
                        List<String> inactiveWorlds = objMob.getInactiveWorlds();
                        if (inactiveWorlds.contains(worldName)) {
                            inactiveWorlds.remove(worldName);
                        } else {
                            objMob.getInactiveWorlds().add(worldName);
                        }
                        objMob.setInactiveWorlds(inactiveWorlds);
                        player.openInventory(UtiMenus.MobWorldsMenu(objMob, Utilities.GetPage(inventory)));
                    }
                }
            }

            case "Mob drops": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                String clickedItemName;
                try {
                    clickedItemName = clickedMeta.getDisplayName().substring(2);
                } catch (Exception e) {
                    clickedItemName = "---";
                }
                switch (clickedItemName) {
                    case "Go back": {
                        player.openInventory(UtiMenus.MobSettingsMenu(objMob));
                        return;
                    }
                    case "Next page": {
                        player.openInventory(UtiMenus.MobDropsMenu(objMob, Utilities.GetPage(inventory) + 1));
                        return;
                    }
                    case "Previous page": {
                        player.openInventory(UtiMenus.MobDropsMenu(objMob, Utilities.GetPage(inventory) - 1));
                        return;
                    }
                    case "Add new item": {
                        LinkedList<ObjDrop> drops = objMob.getDrops();
                        drops.add(new ObjDrop(CustomDrops.defaultDrop.getItem()));
                        objMob.setDrops(drops);
                        player.openInventory(UtiMenus.MobDropsMenu(objMob, Utilities.GetPage(inventory)));
                        return;
                    }
                    default: {
                        int id = Utilities.GetDropId(clickedItem);
                        ObjDrop drop = Utilities.FindDrop(objMob.getDrops(), id);
                        if (drop == null) {
                            return;
                        }
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, drop));
                        return;
                    }
                }
            }

            case "Drop settings": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                ObjDrop objDrop = Utilities.GetDropFromCorner(inventory);
                try {
                    clickedMeta.getDisplayName().substring(2);
                } catch (Exception e) {
                    return;
                }
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.MobDropsMenu(objMob, 1));
                        return;
                    }
                    case "Change drop item": {
                        player.openInventory(UtiMenus.ChangingDropItemMenu(objMob, objDrop, player));
                        return;
                    }
                    case "Item drop chance": {
                        player.closeInventory();
                        CustomDrops.settingChanceMob.put(player.getUniqueId(), objMob);
                        CustomDrops.settingChanceDrop.put(player.getUniqueId(), objDrop);
                        player.sendMessage(ChatColor.DARK_GREEN + "Type in chat the new " + ChatColor.GREEN + "drop chance " + ChatColor.DARK_GREEN + "you want this item to have.");
                        player.sendMessage(ChatColor.DARK_GREEN + "It must be a number " + ChatColor.GREEN + "bewteen 0 and 1 " + ChatColor.DARK_GREEN + "(both included).");
                        return;
                    }
                    case "Drop conditions": {

                        return;
                    }
                    case "Drop commands": {
                        player.openInventory(UtiMenus.DropCommandsMenu(objMob, objDrop, 1));
                        return;
                    }
                    case "Default mob drops": {
                        objDrop.setDefaultDrops(!clickedItem.getType().equals(Material.LIME_CONCRETE));
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                    case "Spawner mob drops": {
                        objDrop.setSpawnerDrops(!clickedItem.getType().equals(Material.LIME_CONCRETE));
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                    case "Spawner egg mob drops": {
                        objDrop.setEggDrops(!clickedItem.getType().equals(Material.LIME_CONCRETE));
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                    case "Drop effect color": {
                        String color = Utilities.GetColorFromLore(clickedMeta.getLore());
                        objDrop.setColor(Utilities.GetNextColor(color));
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                    case "Drop effect shape": {
                        FireworkEffect.Type type = Utilities.GetShapeFromLore(clickedMeta.getLore());
                        objDrop.setShape(Utilities.GetNextShape(type));
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                    case "Toggle drop effects": {
                        objDrop.setEffect(!clickedItem.getType().equals(Material.LIME_CONCRETE));
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                }
            }

            case "Drop item": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                ObjDrop objDrop = Utilities.GetDropFromCorner(inventory);
                if (event.getSlot() < 9 || event.getSlot() > inventory.getSize() - 10) {
                    if (clickedMeta.getDisplayName().substring(2).equals("Go back")) {
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                    }
                    return;
                }
                objDrop.setItem(clickedItem.clone());
                player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                return;
            }

            case "Drop commands": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                ObjDrop objDrop = Utilities.GetDropFromCorner(inventory);
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                    case "Next page": {
                        player.openInventory(UtiMenus.DropCommandsMenu(objMob, objDrop, Utilities.GetPage(inventory) + 1));
                        return;
                    }
                    case "Previous page": {
                        player.openInventory(UtiMenus.DropCommandsMenu(objMob, objDrop, Utilities.GetPage(inventory) - 1));
                        return;
                    }
                    case "Add new command": {
                        player.closeInventory();
                        CustomDrops.addingCommandMob.put(player.getUniqueId(), objMob);
                        CustomDrops.addingCommandDrop.put(player.getUniqueId(), objDrop);
                        player.sendMessage(ChatColor.DARK_GREEN + "Type in chat the " + ChatColor.GREEN + "new command " + ChatColor.DARK_GREEN + "you want this drop to have.");
                        player.sendMessage(ChatColor.DARK_GREEN + "The initial bar " + ChatColor.GREEN + "should not be included" + ChatColor.DARK_GREEN + ".");
                        player.sendMessage(ChatColor.DARK_GREEN + "The following placeholders are supported:");
                        player.sendMessage(ChatColor.DARK_GREEN + "- " + ChatColor.GREEN + "<player>" + ChatColor.DARK_GREEN + ": Name of the player that killed the mob.");
                        player.sendMessage(ChatColor.DARK_GREEN + "- " + ChatColor.GREEN + "<coords>" + ChatColor.DARK_GREEN + ": Coordinates of the mob location.");
                        player.sendMessage(ChatColor.DARK_GREEN + "- " + ChatColor.GREEN + "<all>" + ChatColor.DARK_GREEN + ": Name of all online players.");
                        return;
                    }
                    default: {
                        if (event.isShiftClick() && event.isRightClick()) {
                            for (String command : objDrop.getCommands()) {
                                if (command.toLowerCase().equals(clickedMeta.getDisplayName().substring(2).toLowerCase())) {
                                    objDrop.getCommands().remove(command);
                                    player.openInventory(UtiMenus.DropCommandsMenu(objMob, objDrop, Utilities.GetPage(inventory)));
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }
            }

            case "Drop conditions": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                ObjDrop objDrop = Utilities.GetDropFromCorner(inventory);
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.DropSettingsMenu(objMob, objDrop));
                        return;
                    }
                    case "": {

                    }
                }
            }

            case "": {
                ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
                ObjDrop objDrop = Utilities.GetDropFromCorner(inventory);
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.MobsMenu(1));
                        return;
                    }
                    case "": {

                    }
                }
            }
        }
    }
}
