package com.gmail.cubitverde.DropsEditor;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ObjMob {
    private EntityType type;
    LinkedList<ObjDrop> drops;
    boolean vanillaDrops;
    List<String> inactiveWorlds;

    public ObjMob(EntityType type) {
        this.type = type;
        this.drops = new LinkedList<>();
        this.vanillaDrops = true;
        this.inactiveWorlds = new ArrayList<>();
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public LinkedList<ObjDrop> getDrops() {
        return drops;
    }

    public void setDrops(LinkedList<ObjDrop> drops) {
        this.drops = drops;
    }

    public boolean getVanillaDrops() {
        return vanillaDrops;
    }

    public void setVanillaDrops(boolean vanillaDrops) {
        this.vanillaDrops = vanillaDrops;
    }

    public List<String> getInactiveWorlds() {
        return inactiveWorlds;
    }

    public void setInactiveWorlds(List<String> inactiveWorlds) {
        this.inactiveWorlds = inactiveWorlds;
    }
}
