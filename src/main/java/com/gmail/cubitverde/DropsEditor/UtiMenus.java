package com.gmail.cubitverde.DropsEditor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UtiMenus {
    static Inventory MainMenu() {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "[CustomDrops] Main Menu");
        Utilities.AddGreenFrame(inventory, false);

        inventory.setItem(10, Utilities.CreateNamedItem(Material.ZOMBIE_SPAWN_EGG, ChatColor.GREEN + "Mob drops"));

        inventory.setItem(12, Utilities.CreateNamedItem(Material.DIAMOND_ORE, ChatColor.GREEN + "Block drops"));

        inventory.setItem(14, Utilities.CreateNamedItem(Material.COMMAND_BLOCK, ChatColor.GREEN + "Settings"));

        inventory.setItem(16, Utilities.CreateNamedItem(Material.BARRIER, ChatColor.GRAY + "Coming soon?"));

        return inventory;
    }

    static Inventory MobsMenu(int page) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[CustomDrops] Mob selection");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.OpenPagedMenu(inventory, Utilities.GetMobItemsList(), page, false, "");

        return inventory;
    }

    static Inventory MobSettingsMenu(ObjMob objMob) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "[CustomDrops] Mob settings");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType(), null);

        if (objMob.getVanillaDrops()) {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Vanilla drops are currently enabled.");
            lore.add(ChatColor.DARK_GREEN + "Click this to disable them.");
            inventory.setItem(11, Utilities.CreateNamedItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Toggle vanilla drops", lore));
        } else {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_RED + "Vanilla drops are currently disabled.");
            lore.add(ChatColor.DARK_RED + "Click this to enable them.");
            inventory.setItem(11, Utilities.CreateNamedItem(Material.RED_CONCRETE, ChatColor.RED + "Toggle vanilla drops", lore));
        }

        inventory.setItem(13, Utilities.CreateNamedItem(Material.GOLD_INGOT, ChatColor.GREEN + "Edit custom drops"));

        {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Select in which worlds changes done");
            lore.add(ChatColor.DARK_GREEN + "to this mob will be active.");
            inventory.setItem(15, Utilities.CreateNamedItem(Material.BOOKSHELF, ChatColor.GREEN + "Toggle active worlds", lore));
        }

        return inventory;
    }

    static Inventory MobWorldsMenu(ObjMob objMob, int page) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[CustomDrops] Mob worlds");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType(), null);

        List<String> inactiveWorlds = objMob.getInactiveWorlds();
        LinkedList<ItemStack> worldItems = new LinkedList<>();
        for (World world : CustomDrops.plugin.getServer().getWorlds()) {
            LinkedList<String> lore = new LinkedList<>();
            String worldName = world.getName().toLowerCase();
            if (inactiveWorlds.contains(worldName)) {
                lore.add(ChatColor.DARK_RED + "Changes to this mob are currently disabled");
                lore.add(ChatColor.DARK_RED + "in this world. Click to enable them.");
                worldItems.add(Utilities.CreateNamedItem(Material.RED_BANNER, ChatColor.RED + worldName, lore));
            } else {
                lore.add(ChatColor.DARK_GREEN + "Changes to this mob are currently enabled");
                lore.add(ChatColor.DARK_GREEN + "in this world. Click to disable them.");
                worldItems.add(Utilities.CreateNamedItem(Material.LIME_BANNER, ChatColor.GREEN + worldName, lore));
            }
        }
        Utilities.OpenPagedMenu(inventory, worldItems, page, false, "");

        return inventory;
    }

    static Inventory MobDropsMenu(ObjMob objMob, int page) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[CustomDrops] Mob drops");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType(), null);

        LinkedList<ItemStack> dropItems = new LinkedList<>();
        for (ObjDrop drop :  objMob.getDrops()) {
            ItemStack dropItem = drop.getItem().clone();
            ItemMeta dropMeta = dropItem.getItemMeta();
            List<String> lore;
            if (dropMeta.hasLore()) {
                lore = dropMeta.getLore();
            } else {
                lore = new ArrayList<>();
            }
            lore.add(ChatColor.DARK_GRAY + "Drop ID: " + ChatColor.GRAY + drop.getId());
            dropMeta.setLore(lore);
            dropItem.setItemMeta(dropMeta);
            dropItems.add(dropItem);
        }

        Utilities.OpenPagedMenu(inventory, dropItems, page, true, "item");

        return inventory;
    }

    static Inventory DropSettingsMenu(ObjMob objMob, ObjDrop drop) {
        Inventory inventory = Bukkit.createInventory(null, 45, ChatColor.DARK_GREEN + "[CustomDrops] Drop settings");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType(), drop);

        inventory.setItem(10, drop.getItem());

        {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Click here to change the item.");
            inventory.setItem(11, Utilities.CreateNamedItem(Material.CHEST, ChatColor.GREEN + "Change drop item", lore));
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Current drop chance: " + ChatColor.GREEN + new DecimalFormat("#%.##########").format(drop.getChance()));
            lore.add(ChatColor.DARK_GREEN + "Click here to change the chance.");
            inventory.setItem(12, Utilities.CreateNamedItem(Material.NAME_TAG, ChatColor.GREEN + "Item drop chance", lore));
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Click here to edit the conditions that");
            lore.add(ChatColor.DARK_GREEN + "must be met for the item to drop.");
            inventory.setItem(16, Utilities.CreateNamedItem(Material.COMPARATOR, ChatColor.GREEN + "Drop conditions", lore));
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Click here to edit the commands that");
            lore.add(ChatColor.DARK_GREEN + "will be executed when the item drops.");
            inventory.setItem(15, Utilities.CreateNamedItem(Material.COMMAND_BLOCK, ChatColor.GREEN + "Drop commands", lore));
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            if (drop.getDefaultDrops()) {
                lore.add(ChatColor.DARK_GREEN + "This item will drop from default mobs.");
                lore.add(ChatColor.DARK_GREEN + "Click here to disable default mob drops.");
                inventory.setItem(28, Utilities.CreateNamedItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Default mob drops", lore));
            } else {
                lore.add(ChatColor.DARK_RED + "This item will not drop from default mobs.");
                lore.add(ChatColor.DARK_RED + "Click here to enable default mob drops.");
                inventory.setItem(28, Utilities.CreateNamedItem(Material.RED_CONCRETE, ChatColor.RED + "Default mob drops", lore));
            }
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            if (drop.getSpawnerDrops()) {
                lore.add(ChatColor.DARK_GREEN + "This item will drop from spawner mobs.");
                lore.add(ChatColor.DARK_GREEN + "Click here to disable spawner mob drops.");
                inventory.setItem(29, Utilities.CreateNamedItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Spawner mob drops", lore));
            } else {
                lore.add(ChatColor.DARK_RED + "This item will not drop from spawner mobs.");
                lore.add(ChatColor.DARK_RED + "Click here to enable spawner mob drops.");
                inventory.setItem(29, Utilities.CreateNamedItem(Material.RED_CONCRETE, ChatColor.RED + "Spawner mob drops", lore));
            }
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            if (drop.getEggDrops()) {
                lore.add(ChatColor.DARK_GREEN + "This item will drop from spawner egg mobs.");
                lore.add(ChatColor.DARK_GREEN + "Click here to disable spawner egg mob drops.");
                inventory.setItem(30, Utilities.CreateNamedItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Spawner egg mob drops", lore));
            } else {
                lore.add(ChatColor.DARK_RED + "This item will not drop from spawner egg mobs.");
                lore.add(ChatColor.DARK_RED + "Click here to enable spawner egg mob drops.");
                inventory.setItem(30, Utilities.CreateNamedItem(Material.RED_CONCRETE, ChatColor.RED + "Spawner egg mob drops", lore));
            }
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Current effect color: " + ChatColor.GREEN + drop.getColor());
            lore.add(ChatColor.DARK_GREEN + "Click here to change the effect color.");
            Material material = Material.valueOf(drop.getColor() + "_CONCRETE_POWDER");
            inventory.setItem(32, Utilities.CreateNamedItem(material, ChatColor.GREEN + "Drop effect color", lore));
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Current effect shape: " + ChatColor.GREEN + drop.getShape().toString());
            lore.add(ChatColor.DARK_GREEN + "Click here to change the drop effect shape.");
            inventory.setItem(33, Utilities.CreateNamedItem(Material.FIREWORK_STAR, ChatColor.GREEN + "Drop effect shape", lore));
        }

        {
            LinkedList<String> lore = new LinkedList<>();
            if (drop.getEffect()) {
                lore.add(ChatColor.DARK_GREEN + "Drop effects are currently enabled.");
                lore.add(ChatColor.DARK_GREEN + "Click here to disable them.");
                inventory.setItem(34, Utilities.CreateNamedItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Toggle drop effects", lore));
            } else {
                lore.add(ChatColor.DARK_RED + "Drop effects are currently disabled.");
                lore.add(ChatColor.DARK_RED + "Click here to enable them.");
                inventory.setItem(34, Utilities.CreateNamedItem(Material.RED_CONCRETE, ChatColor.RED + "Toggle drop effects", lore));
            }
        }

        return inventory;
    }

    static Inventory ChangingDropItemMenu(ObjMob objMob, ObjDrop drop, Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[CustomDrops] Drop item");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType(), drop);

        for (int i = 0; i < 36; i++) {
            ItemStack invItem = player.getInventory().getItem(i);
            if (invItem == null) {
                inventory.setItem(i + 9, player.getInventory().getItem(i));
            } else {
                inventory.setItem(i + 9, player.getInventory().getItem(i).clone());
            }
        }

        return inventory;
    }

    static Inventory DropCommandsMenu(ObjMob objMob, ObjDrop drop, int page) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "[CustomDrops] Drop commands");
        Utilities.AddGreenFrame(inventory, true);
        Utilities.AddCornerInfoItem(inventory, objMob.getType(), drop);

        LinkedList<String> commands = drop.getCommands();
        LinkedList<ItemStack> commandItems = new LinkedList<>();
        for (String command : commands) {
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.RED + "Shift right click to delete this command.");
            commandItems.add(Utilities.CreateNamedItem(Material.COMMAND_BLOCK, ChatColor.GREEN + command, lore));
        }

        Utilities.OpenPagedMenu(inventory, commandItems, page, true, "command");

        return inventory;
    }

}
