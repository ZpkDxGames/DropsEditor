package com.gmail.cubitverde.DropsEditor;

import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
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

        FileConfiguration config = getConfig();
        if (config.getConfigurationSection("CustomDrops2") != null) {
            dropId = config.getInt("CustomDrops2.dropId");

            if (config.getConfigurationSection("CustomDrops2.editedMobs") != null) {
                for (String type : config.getConfigurationSection("CustomDrops2.editedMobs").getKeys(false)) {
                    ObjMob objMob = new ObjMob(Utilities.ConvertNameToType(type));

                    objMob.setVanillaDrops(config.getBoolean("CustomDrops2.editedMobs." + type + ".vanillaDrops"));

                    if (config.getConfigurationSection("CustomDrops2.editedMobs." + type + ".inactiveWorlds") != null) {
                        List<String> inactiveWorlds = new ArrayList<>();
                        for (String i : config.getConfigurationSection("CustomDrops2.editedMobs." + type + ".inactiveWorlds").getKeys(false)) {
                            inactiveWorlds.add(config.getString("CustomDrops2.editedMobs." + type + ".inactiveWorlds." + i));
                        }
                        objMob.setInactiveWorlds(inactiveWorlds);
                    }

                    if (config.getConfigurationSection("CustomDrops2.editedMobs." + type + ".drops") != null) {
                        LinkedList<ObjDrop> drops = new LinkedList<>();
                        for (String id : config.getConfigurationSection("CustomDrops2.editedMobs." + type + ".drops").getKeys(false)) {
                            ObjDrop drop = new ObjDrop(CustomDrops.defaultDrop.getItem());

                            drop.setId(Integer.parseInt(id));
                            drop.setItem(config.getItemStack("CustomDrops2.editedMobs." + type + ".drops." + id + ".item"));
                            drop.setChance(config.getDouble("CustomDrops2.editedMobs." + type + ".drops." + id + ".chance"));
                            drop.setColor(config.getString("CustomDrops2.editedMobs." + type + ".drops." + id + ".color"));
                            drop.setShape(FireworkEffect.Type.valueOf(config.getString("CustomDrops2.editedMobs." + type + ".drops." + id + ".shape")));
                            drop.setEffect(config.getBoolean("CustomDrops2.editedMobs." + type + ".drops." + id + ".effect"));
                            drop.setDefaultDrops(config.getBoolean("CustomDrops2.editedMobs." + type + ".drops." + id + ".defaultDrops"));
                            drop.setSpawnerDrops(config.getBoolean("CustomDrops2.editedMobs." + type + ".drops." + id + ".spawnerDrops"));
                            drop.setEggDrops(config.getBoolean("CustomDrops2.editedMobs." + type + ".drops." + id + ".eggDrops"));

                            if (config.getConfigurationSection("CustomDrops2.editedMobs." + type + ".drops." + id + ".commands") != null) {
                                LinkedList<String> commands = new LinkedList<>();
                                for (String i : config.getConfigurationSection("CustomDrops2.editedMobs." + type + ".drops." + id + ".commands").getKeys(false)) {
                                    commands.add(config.getString("CustomDrops2.editedMobs." + type + ".drops." + id + ".commands." + i));
                                }
                                drop.setCommands(commands);
                            }

                            drops.add(drop);
                        }
                        objMob.setDrops(drops);
                    }

                    editedMobs.add(objMob);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        FileConfiguration config = getConfig();
        config.set("CustomDrops2", null);

        config.set("CustomDrops2.dropId", dropId);

        for (ObjMob mob : editedMobs) {
            config.set("CustomDrops2.editedMobs." + mob.getType() + ".vanillaDrops", mob.getVanillaDrops());

            int i = 0;
            for (String worldStr : mob.getInactiveWorlds()) {
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".inactiveWorlds." + i++, worldStr);
            }

            for (ObjDrop drop : mob.getDrops()) {
                int id = drop.getId();
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".item", drop.getItem());
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".chance", drop.getChance());
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".color", drop.getColor());
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".shape", drop.getShape().toString());
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".effect", drop.getEffect());
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".defaultDrops", drop.getDefaultDrops());
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".spawnerDrops", drop.getSpawnerDrops());
                config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".eggDrops", drop.getEggDrops());
                i = 0;
                for (String command : drop.getCommands()) {
                    config.set("CustomDrops2.editedMobs." + mob.getType() + ".drops." + id + ".commands." + i++, command);
                }
            }
        }

        saveConfig();
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
