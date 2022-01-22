package com.gmail.cubitverde.DropsEditor;

import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ObjConditions {
    private Biome biome;
    private boolean checkBiome;
    private String mobName;
    private boolean checkMobName;
    private ItemStack itemUsed;
    private boolean checkItemUsed;
    private boolean itemUsedMeta;
    private boolean checkItemUsedMeta;
    private String permission;
    private boolean checkPermission;
    private List<String> bannedWorlds;
    private boolean checkBannedWorlds;

    public ObjConditions() {

    }

    public ObjConditions(boolean active) {
        this.biome = CustomDrops.defaultConditions.getBiome();
        this.checkBiome = active;
        this.mobName = CustomDrops.defaultConditions.getMobName();
        this.checkMobName = active;
        this.itemUsed = CustomDrops.defaultConditions.getItemUsed();
        this.checkItemUsed = active;
        this.itemUsedMeta = CustomDrops.defaultConditions.getItemUsedMeta();
        this.checkItemUsedMeta = active;
        this.permission = CustomDrops.defaultConditions.getPermission();
        this.checkPermission = active;
        this.bannedWorlds = CustomDrops.defaultConditions.getBannedWorlds();
        this.checkBannedWorlds = active;
    }

    public ObjConditions(ObjConditions objConditions) {
        this.biome = objConditions.getBiome();
        this.checkBiome = objConditions.getCheckBiome();
        this.mobName = objConditions.getMobName();
        this.checkMobName = objConditions.getCheckMobName();
        this.itemUsed = objConditions.getItemUsed();
        this.checkItemUsed = objConditions.getCheckItemUsed();
        this.itemUsedMeta = objConditions.getItemUsedMeta();
        this.checkItemUsedMeta = objConditions.getCheckItemUsedMeta();
        this.permission = objConditions.getPermission();
        this.checkPermission = objConditions.getCheckPermission();
        this.bannedWorlds = objConditions.getBannedWorlds();
        this.checkBannedWorlds = objConditions.getCheckBannedWorlds();
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

    public List<String> getBannedWorlds() {
        return bannedWorlds;
    }

    public void setBannedWorlds(List<String> bannedWorlds) {
        this.bannedWorlds = bannedWorlds;
    }

    public boolean getCheckBannedWorlds() {
        return checkBannedWorlds;
    }

    public void setCheckBannedWorlds(boolean checkBannedWorlds) {
        this.checkBannedWorlds = checkBannedWorlds;
    }
}
