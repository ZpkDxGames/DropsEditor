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
        if (inventory == null || !inventoryView.getTitle().startsWith(ChatColor.DARK_GREEN + "[DropsEditor]")) {
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
                        if (DropsEditor.mobIcons.keySet().contains(mobName)) {
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
                        World world = DropsEditor.plugin.getServer().getWorld(worldName);
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
                        drops.add(new ObjDrop(DropsEditor.defaultDrop.getItem()));
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

                        return;
                    }
                    case "Drop conditions": {

                        return;
                    }
                    case "Drop commands": {

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
