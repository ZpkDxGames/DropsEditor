package com.gmail.cubitverde.DropsEditor;

import org.bukkit.FireworkEffect;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;

public class ObjDrop {
    int id;
    private ItemStack item;
    private double chance;
    private String color;
    private FireworkEffect.Type shape;
    private boolean effect;
    private boolean defaultDrops;
    private boolean spawnerDrops;
    private boolean eggDrops;
    private ObjConditions conditions;
    private LinkedList<String> commands;

    public ObjDrop() {

    }

    public ObjDrop(ItemStack item) {
        this.id = CustomDrops.dropId++;
        this.item = item;
        this.chance = CustomDrops.defaultDrop.getChance();
        this.color = CustomDrops.defaultDrop.getColor();
        this.shape = CustomDrops.defaultDrop.getShape();
        this.effect = CustomDrops.defaultDrop.getEffect();
        this.conditions = CustomDrops.defaultDrop.getConditions();
        this.defaultDrops = CustomDrops.defaultDrop.getDefaultDrops();
        this.spawnerDrops = CustomDrops.defaultDrop.getSpawnerDrops();
        this.eggDrops = CustomDrops.defaultDrop.getEggDrops();
        this.commands = CustomDrops.defaultDrop.getCommands();
    }

    public ObjDrop(ObjDrop objDrop) {
        this.id = CustomDrops.dropId++;
        this.item = objDrop.getItem();
        this.chance = objDrop.getChance();
        this.color = objDrop.getColor();
        this.shape = objDrop.getShape();
        this.conditions = objDrop.getConditions();
        this.defaultDrops = objDrop.getDefaultDrops();
        this.spawnerDrops = objDrop.getSpawnerDrops();
        this.eggDrops = objDrop.getEggDrops();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public FireworkEffect.Type getShape() {
        return shape;
    }

    public void setShape(FireworkEffect.Type shape) {
        this.shape = shape;
    }

    public boolean getEffect() {
        return effect;
    }

    public void setEffect(boolean effect) {
        this.effect = effect;
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

    public LinkedList<String> getCommands() {
        return commands;
    }

    public void setCommands(LinkedList<String> commands) {
        this.commands = commands;
    }
}
