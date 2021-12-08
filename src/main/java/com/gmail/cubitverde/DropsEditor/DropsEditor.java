package com.gmail.cubitverde.DropsEditor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class DropsEditor extends JavaPlugin {
    static DropsEditor plugin;

    @Override
    public void onEnable() {
        plugin = this;

    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true;
    }
}
