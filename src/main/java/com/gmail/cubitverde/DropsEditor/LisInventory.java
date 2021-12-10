package com.gmail.cubitverde.DropsEditor;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LisInventory implements Listener {
    @EventHandler
    private void setEvent (InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        InventoryView inventoryView = event.getView();
        if (inventory == null || !inventoryView.getTitle().startsWith(ChatColor.DARK_GREEN + "[DropsEditor]")) {
            return;
        }
        event.setCancelled(true);

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
                        int page = Integer.parseInt(inventory.getItem(inventory.getSize() - 2).getItemMeta().getDisplayName().substring(18));
                        player.openInventory(UtiMenus.MobsMenu(page + 1));
                        return;
                    }
                    case "Previous page": {
                        int page = Integer.parseInt(inventory.getItem(inventory.getSize() - 2).getItemMeta().getDisplayName().substring(18));
                        player.openInventory(UtiMenus.MobsMenu(page - 1));
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
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "Go back": {
                        player.openInventory(UtiMenus.MobsMenu(1));
                        return;
                    }
                    case "": {

                    }
                }
            }

            case "": {
                switch (clickedMeta.getDisplayName().substring(2)) {
                    case "": {

                    }
                }
            }
        }
    }
}
