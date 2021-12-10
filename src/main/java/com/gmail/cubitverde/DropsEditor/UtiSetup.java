package com.gmail.cubitverde.DropsEditor;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.Biome;

public class UtiSetup {
    static void setupMobIcons(String version) {
        if (version.contains("1.17")) {
            DropsEditor.mobIcons.put("Axolotl", Material.AXOLOTL_SPAWN_EGG);
            DropsEditor.mobIcons.put("Bat", Material.BAT_SPAWN_EGG);
            DropsEditor.mobIcons.put("Bee", Material.BEE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Blaze", Material.BLAZE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Cat", Material.CAT_SPAWN_EGG);
            DropsEditor.mobIcons.put("Cave Spider", Material.CAVE_SPIDER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Chicken", Material.CHICKEN_SPAWN_EGG);
            DropsEditor.mobIcons.put("Cod", Material.COD_SPAWN_EGG);
            DropsEditor.mobIcons.put("Cow", Material.COW_SPAWN_EGG);
            DropsEditor.mobIcons.put("Creeper", Material.CREEPER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Dolphin", Material.DOLPHIN_SPAWN_EGG);
            DropsEditor.mobIcons.put("Donkey", Material.DONKEY_SPAWN_EGG);
            DropsEditor.mobIcons.put("Drowned", Material.DROWNED_SPAWN_EGG);
            DropsEditor.mobIcons.put("Elder Guardian", Material.ELDER_GUARDIAN_SPAWN_EGG);
            DropsEditor.mobIcons.put("Ender Dragon", Material.DRAGON_HEAD);
            DropsEditor.mobIcons.put("Enderman", Material.ENDERMAN_SPAWN_EGG);
            DropsEditor.mobIcons.put("Endermite", Material.ENDERMITE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Evoker", Material.EVOKER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Fox", Material.FOX_SPAWN_EGG);
            DropsEditor.mobIcons.put("Ghast", Material.GHAST_SPAWN_EGG);
            DropsEditor.mobIcons.put("Glow Squid", Material.GLOW_SQUID_SPAWN_EGG);
            DropsEditor.mobIcons.put("Goat", Material.GOAT_SPAWN_EGG);
            DropsEditor.mobIcons.put("Guardian", Material.GUARDIAN_SPAWN_EGG);
            DropsEditor.mobIcons.put("Hoglin", Material.HOGLIN_SPAWN_EGG);
            DropsEditor.mobIcons.put("Horse", Material.HORSE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Husk", Material.HUSK_SPAWN_EGG);
            DropsEditor.mobIcons.put("Iron Golem", Material.IRON_BLOCK);
            DropsEditor.mobIcons.put("Llama", Material.LLAMA_SPAWN_EGG);
            DropsEditor.mobIcons.put("Magma Cube", Material.MAGMA_CUBE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Mule", Material.MULE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Mushroom Cow", Material.MOOSHROOM_SPAWN_EGG);
            DropsEditor.mobIcons.put("Ocelot", Material.OCELOT_SPAWN_EGG);
            DropsEditor.mobIcons.put("Panda", Material.PANDA_SPAWN_EGG);
            DropsEditor.mobIcons.put("Parrot", Material.PARROT_SPAWN_EGG);
            DropsEditor.mobIcons.put("Phantom", Material.PHANTOM_SPAWN_EGG);
            DropsEditor.mobIcons.put("Pig", Material.PIG_SPAWN_EGG);
            DropsEditor.mobIcons.put("Piglin", Material.PIGLIN_SPAWN_EGG);
            DropsEditor.mobIcons.put("Piglin Brute", Material.PIGLIN_BRUTE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Pillager", Material.PILLAGER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Polar Bear", Material.POLAR_BEAR_SPAWN_EGG);
            DropsEditor.mobIcons.put("Pufferfish", Material.PUFFERFISH_SPAWN_EGG);
            DropsEditor.mobIcons.put("Rabbit", Material.RABBIT_SPAWN_EGG);
            DropsEditor.mobIcons.put("Ravager", Material.RAVAGER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Salmon", Material.SALMON_SPAWN_EGG);
            DropsEditor.mobIcons.put("Sheep", Material.SHEEP_SPAWN_EGG);
            DropsEditor.mobIcons.put("Shulker", Material.SHULKER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Silverfish", Material.SILVERFISH_SPAWN_EGG);
            DropsEditor.mobIcons.put("Skeleton", Material.SKELETON_SPAWN_EGG);
            DropsEditor.mobIcons.put("Skeleton Horse", Material.SKELETON_HORSE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Slime", Material.SLIME_SPAWN_EGG);
            DropsEditor.mobIcons.put("Snowman", Material.SNOW_BLOCK);
            DropsEditor.mobIcons.put("Spider", Material.SPIDER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Squid", Material.SQUID_SPAWN_EGG);
            DropsEditor.mobIcons.put("Stray", Material.STRAY_SPAWN_EGG);
            DropsEditor.mobIcons.put("Strider", Material.STRIDER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Trader Llama", Material.TRADER_LLAMA_SPAWN_EGG);
            DropsEditor.mobIcons.put("Tropical Fish", Material.TROPICAL_FISH_SPAWN_EGG);
            DropsEditor.mobIcons.put("Turtle", Material.TURTLE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Vex", Material.VEX_SPAWN_EGG);
            DropsEditor.mobIcons.put("Villager", Material.VILLAGER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Vindicator", Material.VINDICATOR_SPAWN_EGG);
            DropsEditor.mobIcons.put("Wandering Trader", Material.WANDERING_TRADER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Witch", Material.WITCH_SPAWN_EGG);
            DropsEditor.mobIcons.put("Wither", Material.WITHER_SKELETON_SKULL);
            DropsEditor.mobIcons.put("Wither Skeleton", Material.WITHER_SKELETON_SPAWN_EGG);
            DropsEditor.mobIcons.put("Wolf", Material.WOLF_SPAWN_EGG);
            DropsEditor.mobIcons.put("Zombie", Material.ZOMBIE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Zombie Horse", Material.ZOMBIE_HORSE_SPAWN_EGG);
            DropsEditor.mobIcons.put("Zombie Villager", Material.ZOMBIE_VILLAGER_SPAWN_EGG);
            DropsEditor.mobIcons.put("Zombified Piglin", Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);

        } else if (version.contains("1.18")) {
            setupMobIcons("1.17");

        }
    }

    static void setupDefaultConditions() {
        DropsEditor.defaultConditions.setBiome(Biome.PLAINS);
        DropsEditor.defaultConditions.setCheckBiome(false);
        DropsEditor.defaultConditions.setMobName("Zombie");
        DropsEditor.defaultConditions.setCheckMobName(false);
        DropsEditor.defaultConditions.setPermission("droppermission.get");
        DropsEditor.defaultConditions.setCheckPermission(false);
        DropsEditor.defaultConditions.setItemUsed(Utilities.CreateNamedItem(Material.DIAMOND_SWORD, "Drops sword"));
        DropsEditor.defaultConditions.setCheckItemUsed(false);
        DropsEditor.defaultConditions.setItemUsedMeta(true);
        DropsEditor.defaultConditions.setCheckItemUsedMeta(false);
    }

    static void setupDefaultDrop() {
        DropsEditor.defaultDrop.setItem(Utilities.CreateNamedItem(Material.STONE, "Default Item"));
        DropsEditor.defaultDrop.setChance(0.1);
        DropsEditor.defaultDrop.setColor(Color.GREEN);
        DropsEditor.defaultDrop.setShape(FireworkEffect.Type.BURST);
        DropsEditor.defaultDrop.setConditions(DropsEditor.defaultConditions);
        DropsEditor.defaultDrop.setDefaultDrops(true);
        DropsEditor.defaultDrop.setSpawnerDrops(false);
        DropsEditor.defaultDrop.setEggDrops(false);
    }
}
