package com.gmail.cubitverde.DropsEditor;

import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

public class ObjConditions {
    Biome biome;
    boolean checkBiome;
    String mobName;
    boolean checkMobName;
    ItemStack itemUsed;
    boolean checkItemUsed;
    boolean itemUsedMeta;
    boolean checkItemUsedMeta;
    String permission;
    boolean checkPermission;

    public ObjConditions() {

    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public boolean getCheckBiome() {
        return checkBiome;
    }

    public void setCheckBiome(boolean checkBiome) {
        this.checkBiome = checkBiome;
    }

    public String getMobName() {
        return mobName;
    }

    public void setMobName(String mobName) {
        this.mobName = mobName;
    }

    public boolean getCheckMobName() {
        return checkMobName;
    }

    public void setCheckMobName(boolean checkMobName) {
        this.checkMobName = checkMobName;
    }

    public ItemStack getItemUsed() {
        return itemUsed;
    }

    public void setItemUsed(ItemStack itemUsed) {
        this.itemUsed = itemUsed;
    }

    public boolean getCheckItemUsed() {
        return checkItemUsed;
    }

    public void setCheckItemUsed(boolean checkItemUsed) {
        this.checkItemUsed = checkItemUsed;
    }

    public boolean getItemUsedMeta() {
        return itemUsedMeta;
    }

    public void setItemUsedMeta(boolean itemUsedMeta) {
        this.itemUsedMeta = itemUsedMeta;
    }

    public boolean getCheckItemUsedMeta() {
        return checkItemUsedMeta;
    }

    public void setCheckItemUsedMeta(boolean checkItemUsedMeta) {
        this.checkItemUsedMeta = checkItemUsedMeta;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean getCheckPermission() {
        return checkPermission;
    }

    public void setCheckPermission(boolean checkPermission) {
        this.checkPermission = checkPermission;
    }
}
