package com.gmail.cubitverde.DropsEditor;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.ArrayList;
import java.util.LinkedList;

public class UtiSetup {
    static void setupMobIcons(String version) {
        if (version.contains("1.17")) {
            CustomDrops.mobIcons.put("Axolotl", Material.AXOLOTL_SPAWN_EGG);
            CustomDrops.mobIcons.put("Bat", Material.BAT_SPAWN_EGG);
            CustomDrops.mobIcons.put("Bee", Material.BEE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Blaze", Material.BLAZE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Cat", Material.CAT_SPAWN_EGG);
            CustomDrops.mobIcons.put("Cave Spider", Material.CAVE_SPIDER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Chicken", Material.CHICKEN_SPAWN_EGG);
            CustomDrops.mobIcons.put("Cod", Material.COD_SPAWN_EGG);
            CustomDrops.mobIcons.put("Cow", Material.COW_SPAWN_EGG);
            CustomDrops.mobIcons.put("Creeper", Material.CREEPER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Dolphin", Material.DOLPHIN_SPAWN_EGG);
            CustomDrops.mobIcons.put("Donkey", Material.DONKEY_SPAWN_EGG);
            CustomDrops.mobIcons.put("Drowned", Material.DROWNED_SPAWN_EGG);
            CustomDrops.mobIcons.put("Elder Guardian", Material.ELDER_GUARDIAN_SPAWN_EGG);
            CustomDrops.mobIcons.put("Ender Dragon", Material.DRAGON_HEAD);
            CustomDrops.mobIcons.put("Enderman", Material.ENDERMAN_SPAWN_EGG);
            CustomDrops.mobIcons.put("Endermite", Material.ENDERMITE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Evoker", Material.EVOKER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Fox", Material.FOX_SPAWN_EGG);
            CustomDrops.mobIcons.put("Ghast", Material.GHAST_SPAWN_EGG);
            CustomDrops.mobIcons.put("Glow Squid", Material.GLOW_SQUID_SPAWN_EGG);
            CustomDrops.mobIcons.put("Goat", Material.GOAT_SPAWN_EGG);
            CustomDrops.mobIcons.put("Guardian", Material.GUARDIAN_SPAWN_EGG);
            CustomDrops.mobIcons.put("Hoglin", Material.HOGLIN_SPAWN_EGG);
            CustomDrops.mobIcons.put("Horse", Material.HORSE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Husk", Material.HUSK_SPAWN_EGG);
            CustomDrops.mobIcons.put("Iron Golem", Material.IRON_BLOCK);
            CustomDrops.mobIcons.put("Llama", Material.LLAMA_SPAWN_EGG);
            CustomDrops.mobIcons.put("Magma Cube", Material.MAGMA_CUBE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Mule", Material.MULE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Mushroom Cow", Material.MOOSHROOM_SPAWN_EGG);
            CustomDrops.mobIcons.put("Ocelot", Material.OCELOT_SPAWN_EGG);
            CustomDrops.mobIcons.put("Panda", Material.PANDA_SPAWN_EGG);
            CustomDrops.mobIcons.put("Parrot", Material.PARROT_SPAWN_EGG);
            CustomDrops.mobIcons.put("Phantom", Material.PHANTOM_SPAWN_EGG);
            CustomDrops.mobIcons.put("Pig", Material.PIG_SPAWN_EGG);
            CustomDrops.mobIcons.put("Piglin", Material.PIGLIN_SPAWN_EGG);
            CustomDrops.mobIcons.put("Piglin Brute", Material.PIGLIN_BRUTE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Pillager", Material.PILLAGER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Polar Bear", Material.POLAR_BEAR_SPAWN_EGG);
            CustomDrops.mobIcons.put("Pufferfish", Material.PUFFERFISH_SPAWN_EGG);
            CustomDrops.mobIcons.put("Rabbit", Material.RABBIT_SPAWN_EGG);
            CustomDrops.mobIcons.put("Ravager", Material.RAVAGER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Salmon", Material.SALMON_SPAWN_EGG);
            CustomDrops.mobIcons.put("Sheep", Material.SHEEP_SPAWN_EGG);
            CustomDrops.mobIcons.put("Shulker", Material.SHULKER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Silverfish", Material.SILVERFISH_SPAWN_EGG);
            CustomDrops.mobIcons.put("Skeleton", Material.SKELETON_SPAWN_EGG);
            CustomDrops.mobIcons.put("Skeleton Horse", Material.SKELETON_HORSE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Slime", Material.SLIME_SPAWN_EGG);
            CustomDrops.mobIcons.put("Snowman", Material.SNOW_BLOCK);
            CustomDrops.mobIcons.put("Spider", Material.SPIDER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Squid", Material.SQUID_SPAWN_EGG);
            CustomDrops.mobIcons.put("Stray", Material.STRAY_SPAWN_EGG);
            CustomDrops.mobIcons.put("Strider", Material.STRIDER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Trader Llama", Material.TRADER_LLAMA_SPAWN_EGG);
            CustomDrops.mobIcons.put("Tropical Fish", Material.TROPICAL_FISH_SPAWN_EGG);
            CustomDrops.mobIcons.put("Turtle", Material.TURTLE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Vex", Material.VEX_SPAWN_EGG);
            CustomDrops.mobIcons.put("Villager", Material.VILLAGER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Vindicator", Material.VINDICATOR_SPAWN_EGG);
            CustomDrops.mobIcons.put("Wandering Trader", Material.WANDERING_TRADER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Witch", Material.WITCH_SPAWN_EGG);
            CustomDrops.mobIcons.put("Wither", Material.WITHER_SKELETON_SKULL);
            CustomDrops.mobIcons.put("Wither Skeleton", Material.WITHER_SKELETON_SPAWN_EGG);
            CustomDrops.mobIcons.put("Wolf", Material.WOLF_SPAWN_EGG);
            CustomDrops.mobIcons.put("Zombie", Material.ZOMBIE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Zombie Horse", Material.ZOMBIE_HORSE_SPAWN_EGG);
            CustomDrops.mobIcons.put("Zombie Villager", Material.ZOMBIE_VILLAGER_SPAWN_EGG);
            CustomDrops.mobIcons.put("Zombified Piglin", Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);

        } else if (version.contains("1.18")) {
            setupMobIcons("1.17");
        }
    }

    static void setupDefaultConditions() {
        CustomDrops.defaultConditions.setBiome(Biome.PLAINS);
        CustomDrops.defaultConditions.setCheckBiome(false);
        CustomDrops.defaultConditions.setMobName("Zombie");
        CustomDrops.defaultConditions.setCheckMobName(false);
        CustomDrops.defaultConditions.setPermission("droppermission.get");
        CustomDrops.defaultConditions.setCheckPermission(false);
        CustomDrops.defaultConditions.setItemUsed(Utilities.CreateNamedItem(Material.DIAMOND_SWORD, "Drops sword"));
        CustomDrops.defaultConditions.setCheckItemUsed(false);
        CustomDrops.defaultConditions.setItemUsedMeta(false);
        CustomDrops.defaultConditions.setCheckItemUsedMeta(false);
        CustomDrops.defaultConditions.setBannedWorlds(new ArrayList<>());
        CustomDrops.defaultConditions.setCheckBannedWorlds(false);
    }

    static void setupDefaultDrop() {
        CustomDrops.defaultDrop.setItem(Utilities.CreateNamedItem(Material.STONE, "New Item"));
        CustomDrops.defaultDrop.setChance(0.1);
        CustomDrops.defaultDrop.setColor("LIME");
        CustomDrops.defaultDrop.setShape(FireworkEffect.Type.BURST);
        CustomDrops.defaultDrop.setConditions(CustomDrops.defaultConditions);
        CustomDrops.defaultDrop.setDefaultDrops(true);
        CustomDrops.defaultDrop.setSpawnerDrops(false);
        CustomDrops.defaultDrop.setEggDrops(false);
        CustomDrops.defaultDrop.setEffect(true);
        CustomDrops.defaultDrop.setCommands(new LinkedList<>());
    }
}
