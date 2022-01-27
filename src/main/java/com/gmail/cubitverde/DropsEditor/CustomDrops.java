package com.gmail.cubitverde.DropsEditor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class CustomDrops extends JavaPlugin {
    static CustomDrops plugin;
    static Map<String, Material> mobIcons = new HashMap<>();
    static List<ObjMob> editedMobs = new ArrayList<>();
    static ObjDrop defaultDrop = new ObjDrop();
    static ObjConditions defaultConditions = new ObjConditions();
    static int dropId = 0;

    static Map<UUID, ObjMob> settingChanceMob = new HashMap<>();
    static Map<UUID, ObjDrop> settingChanceDrop = new HashMap<>();
    static Map<UUID, ObjMob> addingCommandMob = new HashMap<>();
    static Map<UUID, ObjDrop> addingCommandDrop = new HashMap<>();
    static List<Firework> dropFireworks = new ArrayList<>();
    static List<UUID> spawnerMobs = new ArrayList<>();
    static List<UUID> spawnereggMobs = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new LisInventory(), this);
        getServer().getPluginManager().registerEvents(new LisChat(), this);
        getServer().getPluginManager().registerEvents(new LisMobdeath(), this);
        getServer().getPluginManager().registerEvents(new LisDamaged(), this);
        getServer().getPluginManager().registerEvents(new LisMobspawn(), this);

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
