package me.rdev.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public final class ItemStackUtils {

    public static ItemStack createItemStack(Material mat, int amount, String name) {
        ItemStack is = new ItemStack(mat, amount);
        ItemMeta m = is.hasItemMeta() ? is.getItemMeta() : Bukkit.getItemFactory().getItemMeta(mat);
        m.setDisplayName(name);
        is.setItemMeta(m);
        return is;
    }

    public static ItemStack createItemStack(Material mat, int amount, String name, String[] lore) {
        ItemStack is = createItemStack(mat, amount, name);
        ItemMeta m = is.hasItemMeta() ? is.getItemMeta() : Bukkit.getItemFactory().getItemMeta(mat);
        m.setLore(Arrays.asList(lore));
        is.setItemMeta(m);
        return is;
    }

    public static ItemStack getSkull(String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (texture == null) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignore) {}
        head.setItemMeta(headMeta);
        return head;
    }
}
