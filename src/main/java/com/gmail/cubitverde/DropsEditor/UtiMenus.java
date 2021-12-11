package com.gmail.cubitverde.DropsEditor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UtiMenus {
    static Inventory MainMenu() {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "[DropsEditor] Main Menu");
        Utilities.AddGreenFrame(inventory, false);

        inventory.setItem(10, Utilities.CreateNamedItem(Material.ZOMBIE_SPAWN_EGG, ChatColor.GREEN + "Mob drops"));

        inventory.setItem(12, Utilities.CreateNamedItem(Material.DIAMOND_ORE, ChatColor.GREEN + "Block drops"));

        inventory.setItem(14, Utilities.CreateNamedItem(Material.COMMAND_BLOCK, ChatColor.GREEN + "Settings"));

        inventory.setItem(16, Utilities.CreateNamedItem(Material.BARRIER, ChatColor.GRAY + "Coming soon?"));

        return inventory;
    }

    static Inventory MobsMenu(int page) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[DropsEditor] Mob selection");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.OpenPagedMenu(inventory, Utilities.GetMobItemsList(), page, false);

        return inventory;
    }

    static Inventory MobSettingsMenu(ObjMob objMob) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "[DropsEditor] Mob settings");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType());

        if (objMob.getVanillaDrops()) {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_GREEN + "Vanilla drops are currently enabled.");
            lore.add(ChatColor.DARK_GREEN + "Click this to disable them.");
            inventory.setItem(11, Utilities.CreateNamedItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Toggle vanilla drops", lore));
        } else {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "Vanilla drops are currently disabled.");
            lore.add(ChatColor.DARK_RED + "Click this to enable them.");
            inventory.setItem(11, Utilities.CreateNamedItem(Material.RED_CONCRETE, ChatColor.RED + "Toggle vanilla drops", lore));
        }

        inventory.setItem(13, Utilities.CreateNamedItem(Material.GOLD_INGOT, ChatColor.GREEN + "Edit custom drops"));

        {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_GREEN + "Select in which worlds changes done");
            lore.add(ChatColor.DARK_GREEN + "to this mob will be active.");
            inventory.setItem(15, Utilities.CreateNamedItem(Material.BOOKSHELF, ChatColor.GREEN + "Toggle active worlds", lore));
        }

        return inventory;
    }

    static Inventory MobWorldsMenu(ObjMob objMob) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[DropsEditor] Mob worlds");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType());

        List<String> inactiveWorlds = objMob.getInactiveWorlds();
        LinkedList<ItemStack> worldItems = new LinkedList<>();
        for (World world : DropsEditor.plugin.getServer().getWorlds()) {
            List<String> lore = new ArrayList<>();
            String worldName = world.getName().toLowerCase();
            if (inactiveWorlds.contains(worldName)) {
                lore.add(ChatColor.DARK_RED + "Changes to this mob in this world are");
                lore.add(ChatColor.DARK_RED + "currently disabled. Click to enable them.");
                worldItems.add(Utilities.CreateNamedItem(Material.RED_BANNER, ChatColor.RED + worldName, lore));
            } else {
                lore.add(ChatColor.DARK_GREEN + "Changes to this mob in this world are");
                lore.add(ChatColor.DARK_GREEN + "currently enabled. Click to disable them.");
                worldItems.add(Utilities.CreateNamedItem(Material.LIME_BANNER, ChatColor.GREEN + worldName, lore));
            }
        }
        Utilities.OpenPagedMenu(inventory, worldItems, 1, false);

        return inventory;
    }

    static Inventory MobDropsMenu(ObjMob objMob) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[DropsEditor] Mob drops");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType());

        LinkedList<ItemStack> dropItems = new LinkedList<>();
        for (ObjDrop drop :  objMob.getDrops()) {
            dropItems.add(drop.getItem());
        }

        Utilities.OpenPagedMenu(inventory, dropItems, 1, true);

        return inventory;
    }
}
