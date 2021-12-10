package com.gmail.cubitverde.DropsEditor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
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
            lore.add(ChatColor.GREEN + "Vanilla drops are currently enabled.");
            lore.add(ChatColor.GREEN + "Click this to disable them.");
            inventory.setItem(11, Utilities.CreateNamedItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Toggle vanilla drops", lore));
        } else {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.RED + "Vanilla drops are currently disabled.");
            lore.add(ChatColor.RED + "Click this to enable them.");
            inventory.setItem(11, Utilities.CreateNamedItem(Material.RED_CONCRETE, ChatColor.RED + "Toggle vanilla drops", lore));
        }

        inventory.setItem(13, Utilities.CreateNamedItem(Material.GOLD_INGOT, ChatColor.GREEN + "Edit custom drops"));

        {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Select in which worlds the plugin will");
            lore.add(ChatColor.GREEN + "be active at and change this mob's drops.");
            inventory.setItem(15, Utilities.CreateNamedItem(Material.BOOKSHELF, ChatColor.GREEN + "Toggle active worlds", lore));
        }

        return inventory;
    }
}
