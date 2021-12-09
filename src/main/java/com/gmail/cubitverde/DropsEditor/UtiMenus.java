package com.gmail.cubitverde.DropsEditor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

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
}
