package com.gmail.cubitverde.DropsEditor;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

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

    static ItemStack CreateNamedItem(Material material, String name, LinkedList<String> lore) {
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
            LinkedList<String> lore = new LinkedList<>();
            lore.add(ChatColor.DARK_GREEN + "Created by: " + ChatColor.GREEN + "cubito_verde");
            inventory.setItem(4, CreateNamedItem(Material.WHITE_STAINED_GLASS_PANE, ChatColor.GREEN + "[Drops Editor]", lore));
        }

        if (addBackButton) {
            inventory.setItem(size - 9, CreateNamedItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Go back"));
        }
    }

    static LinkedList<ItemStack> GetMobItemsList() {
        LinkedList<ItemStack> list = new LinkedList<>();
        LinkedList<String> namesList = new LinkedList<>(CustomDrops.mobIcons.keySet());
        Collections.sort(namesList);
        for (String name : namesList) {
            list.add(CreateNamedItem(CustomDrops.mobIcons.get(name), ChatColor.GREEN + name));
        }
        return list;
    }

    static void OpenPagedMenu(Inventory inventory, LinkedList<ItemStack> items, int page, boolean addItems, String addNew) {
        LinkedList<Integer> inventoryInside = InventoryInside(inventory.getSize());
        int inventorySize = inventory.getSize();
        int inventoryInsideSize = inventoryInside.size();
        int listSize = items.size();

        boolean reachedEnd = false;
        int startSlot = (page - 1) * inventoryInsideSize;
        for (int i = 0; i < inventoryInsideSize; i++) {
            if (startSlot + i >= listSize) {
                if (addItems) {
                    inventory.setItem(inventoryInside.get(i), CreateNamedItem(Material.LIME_DYE, ChatColor.GREEN + "Add new " + addNew));
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
        for (ObjMob tempObjMob : CustomDrops.editedMobs) {
            if (tempObjMob.getType().equals(type)) {
                return tempObjMob;
            }
        }

        ObjMob objMob = new ObjMob(type);
        CustomDrops.editedMobs.add(objMob);
        return objMob;
    }

    static void AddCornerInfoItem(Inventory inventory, EntityType type, ObjDrop drop) {
        ItemStack cornerItem = inventory.getItem(0);
        ItemMeta cornerMeta = cornerItem.getItemMeta();
        List<String> cornerLore = new ArrayList<>();
        cornerLore.add(ChatColor.DARK_GRAY + "Editing mob: " + ChatColor.GRAY + type.toString());
        if (drop != null) {
            cornerLore.add(ChatColor.DARK_GRAY + "Drop ID: " + ChatColor.GRAY + drop.getId());
        }
        cornerMeta.setLore(cornerLore);
        cornerItem.setItemMeta(cornerMeta);
        cornerItem.setItemMeta(cornerMeta);
        inventory.setItem(0, cornerItem);
    }

    static ObjMob GetObjMobFromCorner(Inventory inventory) {
        ItemStack cornerItem = inventory.getItem(0);
        ItemMeta cornerMeta = cornerItem.getItemMeta();
        List<String> cornerLore = cornerMeta.getLore();
        String mobType = cornerLore.get(0).substring(17);
        return GetObjMob(mobType);
    }

    static int GetPage(Inventory inventory) {
        return Integer.parseInt(inventory.getItem(inventory.getSize() - 2).getItemMeta().getDisplayName().substring(18));
    }

    static int GetDropId(ItemStack item) {
        try {
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = itemMeta.getLore();
            return Integer.parseInt(lore.get(lore.size() - 1).substring(13));
        } catch (Exception e) {
            return -1;
        }
    }

    static ObjDrop FindDrop(LinkedList<ObjDrop> drops, int id) {
        ObjDrop drop = null;
        for (ObjDrop tempDrop : drops) {
            if (tempDrop.getId() == id) {
                drop = tempDrop;
                break;
            }
        }
        return drop;
    }

    static ObjDrop GetDropFromCorner(Inventory inventory) {
        ItemStack cornerItem = inventory.getItem(0);
        ItemMeta cornerMeta = cornerItem.getItemMeta();
        List<String> cornerLore = cornerMeta.getLore();
        int id = Integer.parseInt(cornerLore.get(1).substring(13));
        ObjMob objMob = Utilities.GetObjMobFromCorner(inventory);
        return FindDrop(objMob.getDrops(), id);
    }

    static String GetNextColor(String color) {
        LinkedList<String> colors = new LinkedList<>();
        colors.add("BLACK");
        colors.add("BLUE");
        colors.add("BROWN");
        colors.add("CYAN");
        colors.add("GRAY");
        colors.add("GREEN");
        colors.add("LIGHT_BLUE");
        colors.add("LIGHT_GRAY");
        colors.add("LIME");
        colors.add("MAGENTA");
        colors.add("ORANGE");
        colors.add("PINK");
        colors.add("PURPLE");
        colors.add("RED");
        colors.add("WHITE");
        colors.add("YELLOW");

        int i = 0;
        for (String tempColor : colors) {
            if (tempColor.equals(color)) {
                if (i == colors.size() - 1) {
                    return colors.get(0);
                } else {
                    return colors.get(++i);
                }
            }
            i++;
        }

        return null;
    }

    static String GetColorFromLore(List<String> lore) {
        return lore.get(0).substring(26);
    }

    static FireworkEffect.Type GetNextShape(FireworkEffect.Type type) {
        LinkedList<FireworkEffect.Type> types = new LinkedList<>();
        types.add(FireworkEffect.Type.BURST);
        types.add(FireworkEffect.Type.BALL_LARGE);
        types.add(FireworkEffect.Type.BALL);
        types.add(FireworkEffect.Type.STAR);
        types.add(FireworkEffect.Type.CREEPER);

        int i = 0;
        for (FireworkEffect.Type tempType : types) {
            if (tempType.equals(type)) {
                if (tempType.equals(types.get(types.size() - 1))) {
                    return types.get(0);
                } else {
                    return types.get(i + 1);
                }
            }
            i++;
        }

        return null;
    }

    static FireworkEffect.Type GetShapeFromLore(List<String> lore) {
        return FireworkEffect.Type.valueOf(lore.get(0).substring(26));
    }

    static Color GetColorFromString(String colorString) {
        String string = colorString.toLowerCase();
        switch (string) {
            case "black": {
                return Color.fromBGR(33, 29, 29);
            }
            case "blue": {
                return Color.fromBGR(169, 68, 60);
            }
            case "brown": {
                return Color.fromBGR(50, 84, 130);
            }
            case "cyan": {
                return Color.fromBGR(157, 156, 22);
            }
            case "gray": {
                return Color.fromBGR(82, 79, 71);
            }
            case "green": {
                return Color.fromBGR(21, 124, 93);
            }
            case "light_blue": {
                return Color.fromBGR(218, 179, 58);
            }
            case "light_gray": {
                return Color.fromBGR(151, 157, 156);
            }
            case "lime": {
                return Color.fromBGR(31, 199, 128);
            }
            case "magenta": {
                return Color.fromBGR(189, 79, 198);
            }
            case "orange": {
                return Color.fromBGR(29, 128, 249);
            }
            case "pink": {
                return Color.fromBGR(170, 140, 243);
            }
            case "purple": {
                return Color.fromBGR(183, 50, 137);
            }
            case "red": {
                return Color.fromBGR(38, 46, 176);
            }
            case "white": {
                return Color.fromBGR(255, 255, 249);
            }
            case "yellow": {
                return Color.fromBGR(61, 216, 255);
            }
        }

        return null;
    }

    static List<String> readCommandPlaceholders(String command, Player player, Location location) {
        List<String> returnCommands = new ArrayList<>();
        Map<Integer, Integer> placeholders = new HashMap<>();
        Map<Integer, Integer> placeholdersAll = new HashMap<>();

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == '<') {
                for (int j = i + 1; j < command.length(); j++) {
                    if (command.charAt(j) == '>') {
                        placeholders.put(i, j);
                        i = j;
                        break;
                    }
                }
            }
        }

        int offset = 0;
        for (int i : placeholders.keySet()) {
            int j = placeholders.get(i);
            switch (command.substring(i + 1 + offset, j + offset)) {
                case "player": {
                    if (player == null) {
                        return null;
                    }
                    command = command.substring(0, i + offset) + player.getName() + command.substring(j + 1 + offset);
                    offset = offset - 8 + player.getName().length();
                    break;
                }
                case "coords": {
                    String locString = location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
                    command = command.substring(0, i + offset) + locString + command.substring(j + 1 + offset);
                    offset = offset - 8 + locString.length();
                    break;
                }
                case "all": {
                    placeholdersAll.put(i, j);
                    break;
                }
            }
        }

        for (int i : placeholdersAll.keySet()) {
            int j = placeholdersAll.get(i);
            for (Player onlinePlayer : CustomDrops.plugin.getServer().getOnlinePlayers()) {
                String newCommand = command.substring(0, i + offset) + onlinePlayer.getName() + command.substring(j + 1 + offset);
                returnCommands.add(newCommand);
            }
        }

        if (placeholdersAll.keySet().size() == 0) {
            returnCommands.add(command);
        }

        return returnCommands;
    }
}
