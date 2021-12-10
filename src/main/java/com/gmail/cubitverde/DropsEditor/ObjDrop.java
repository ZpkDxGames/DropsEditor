package com.gmail.cubitverde.DropsEditor;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.ItemStack;

public class ObjDrop {
    private ItemStack item;
    private double chance;
    private Color color;
    private FireworkEffect.Type shape;
    private boolean defaultDrops;
    private boolean spawnerDrops;
    private boolean eggDrops;
    private ObjConditions conditions;

    public ObjDrop() {

    }

    public ObjDrop(ItemStack item) {
        this.item = item;
        this.chance = DropsEditor.defaultDrop.getChance();
        this.color = DropsEditor.defaultDrop.getColor();
        this.shape = DropsEditor.defaultDrop.getShape();
        this.conditions = DropsEditor.defaultDrop.getConditions();
        this.defaultDrops = DropsEditor.defaultDrop.getDefaultDrops();
        this.spawnerDrops = DropsEditor.defaultDrop.getSpawnerDrops();
        this.eggDrops = DropsEditor.defaultDrop.getEggDrops();
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FireworkEffect.Type getShape() {
        return shape;
    }

    public void setShape(FireworkEffect.Type shape) {
        this.shape = shape;
    }

    public boolean getDefaultDrops() {
        return defaultDrops;
    }

    public void setDefaultDrops(boolean defaultDrops) {
        this.defaultDrops = defaultDrops;
    }

    public boolean getSpawnerDrops() {
        return spawnerDrops;
    }

    public void setSpawnerDrops(boolean spawnerDrops) {
        this.spawnerDrops = spawnerDrops;
    }

    public boolean getEggDrops() {
        return eggDrops;
    }

    public void setEggDrops(boolean eggDrops) {
        this.eggDrops = eggDrops;
    }

    public ObjConditions getConditions() {
        return conditions;
    }

    public void setConditions(ObjConditions conditions) {
        this.conditions = conditions;
    }
}
