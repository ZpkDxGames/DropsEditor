package com.gmail.cubitverde.DropsEditor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utilities {
    static List<Integer> InventoryFrame(int size) {
        List<Integer> frame = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i < 9 || size - i < 9 || i % 9 == 0 || i % 9 == 8) {
                frame.add(i);
            }
        }
        return frame;
    }

    static LinkedList<Integer> InventoryInside(int size) {
        LinkedList<Integer> inside = new LinkedList<>();
        List<Integer> frame = InventoryFrame(size);
        for (int i = 0; i < size; i++) {
            if (!frame.contains(i)) {
                inside.add(i);
            }
        }
        return inside;
    }

    static ItemStack CreateNamedItem(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    static ItemStack CreateNamedItem(Material material, String name, List<String> lore) {
        ItemStack itemStack = CreateNamedItem(material, name);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    static void AddGreenFrame(Inventory inventory, boolean addBackButton) {
        int size = inventory.getSize();

        for (int i = 0; i < size; i++) {
            inventory.setItem(i, CreateNamedItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " "));
        }

        for (int i : InventoryFrame(size)) {
            inventory.setItem(i, CreateNamedItem(Material.LIME_STAINED_GLASS_PANE, " "));
        }

        {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_GREEN + "Created by: " + ChatColor.GREEN + "cubito_verde");
            inventory.setItem(4, CreateNamedItem(Material.WHITE_STAINED_GLASS_PANE, ChatColor.GREEN + "[Drops Editor]", lore));
        }

        if (addBackButton) {
            inventory.setItem(size - 9, CreateNamedItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Go back"));
        }
    }

    static LinkedList<ItemStack> GetMobItemsList() {
        LinkedList<ItemStack> list = new LinkedList<>();
        LinkedList<String> namesList = new LinkedList<>(DropsEditor.mobIcons.keySet());
        Collections.sort(namesList);
        for (String name : namesList) {
            list.add(CreateNamedItem(DropsEditor.mobIcons.get(name), ChatColor.GREEN + name));
        }
        return list;
    }

    static void OpenPagedMenu(Inventory inventory, LinkedList<ItemStack> items, int page, boolean addItems) {
        LinkedList<Integer> inventoryInside = InventoryInside(inventory.getSize());
        int inventorySize = inventory.getSize();
        int inventoryInsideSize = inventoryInside.size();
        int listSize = items.size();

        boolean reachedEnd = false;
        int startSlot = (page - 1) * inventoryInsideSize;
        for (int i = 0; i < inventoryInsideSize; i++) {
            if (startSlot + i >= listSize) {
                if (addItems) {
                    inventory.setItem(inventoryInside.get(i), CreateNamedItem(Material.LIME_DYE, ChatColor.GREEN + "Add new item"));
                }
                break;
            }
            inventory.setItem(inventoryInside.get(i), items.get(startSlot + i));
            if (i == inventoryInsideSize - 1) {
                reachedEnd = true;
            }
        }

        inventory.setItem(inventorySize - 2, CreateNamedItem(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.DARK_GREEN + "Current page: " + ChatColor.GREEN + page));

        if (page > 1) {
            inventory.setItem(inventorySize - 3, CreateNamedItem(Material.ORANGE_STAINED_GLASS_PANE, ChatColor.GREEN + "Previous page"));
        }

        if (addItems) {
            if (reachedEnd) {
                inventory.setItem(inventorySize - 1, CreateNamedItem(Material.ORANGE_STAINED_GLASS_PANE, ChatColor.GREEN + "Next page"));
            }
        } else {
            if (reachedEnd && (startSlot + inventoryInsideSize) < listSize) {
                inventory.setItem(inventorySize - 1, CreateNamedItem(Material.ORANGE_STAINED_GLASS_PANE, ChatColor.GREEN + "Next page"));
            }
        }
    }

    static EntityType ConvertNameToType(String name) {
        String convertedName = name.toUpperCase();
        convertedName = convertedName.replace(' ', '_');
        return EntityType.valueOf(convertedName);
    }

    static ObjMob GetObjMob(String name) {
        EntityType type = ConvertNameToType(name);
        for (ObjMob tempObjMob : DropsEditor.editedMobs) {
            if (tempObjMob.getType().equals(type)) {
                return tempObjMob;
            }
        }

        ObjMob objMob = new ObjMob(type);
        DropsEditor.editedMobs.add(objMob);
        return objMob;
    }

    static void AddCornerInfoItem(Inventory inventory, EntityType type) {
        ItemStack cornerItem = inventory.getItem(0);
        ItemMeta cornerMeta = cornerItem.getItemMeta();
        List<String> cornerLore = new ArrayList<>();
        cornerLore.add(ChatColor.GRAY + "Editing mob: " + ChatColor.WHITE + type.toString());
        cornerMeta.setLore(cornerLore);
        cornerItem.setItemMeta(cornerMeta);
        cornerItem.setItemMeta(cornerMeta);
        inventory.setItem(0, cornerItem);
    }
}
