package com.gmail.cubitverde.DropsEditor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DropsEditor extends JavaPlugin {
    static DropsEditor plugin;
    static Map<String, Material> mobIcons = new HashMap<>();
    static List<ObjMob> editedMobs = new ArrayList<>();
    static ObjDrop defaultDrop = new ObjDrop();
    static ObjConditions defaultConditions = new ObjConditions();

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new LisInventory(), this);

        UtiSetup.setupMobIcons(plugin.getServer().getVersion());
        UtiSetup.setupDefaultConditions();
        UtiSetup.setupDefaultDrop();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "This plugin can only be used by players.");
            return true;
        }
        Player player = (Player) sender;

        player.openInventory(UtiMenus.MainMenu());

        return true;
    }
}
