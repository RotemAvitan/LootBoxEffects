package me.rdev.effects;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum CubeEffectType {

    LOVE_EFFECT(0, Material.APPLE, ChatColor.BLUE + "Love Effect"
            , new String[] {ChatColor.YELLOW + "The first effect of the amazing RDev_!"}),

    RAINBOW_EFFECT(1, Material.MAGMA_CREAM, ChatColor.BLUE + "Rainbow Effect"
            , new String[] {ChatColor.YELLOW + "Rainbow! so lovely."});

    @Getter private int slot;
    @Getter private Material material;
    @Getter private String name;
    @Getter private String[] lore;


    CubeEffectType(int slot, Material material, String name, String[] lore) {
        this.slot = slot;
        this.material = material;
        this.name = name;
        this.lore = lore;
    }
}
