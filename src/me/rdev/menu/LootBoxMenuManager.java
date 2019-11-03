package me.rdev.menu;

import lombok.Getter;
import me.rdev.effects.CubeEffectType;
import me.rdev.utils.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class LootBoxMenuManager {

    static final String MENU_TITLE = "Choose effect!";
    @Getter private Map<UUID, Location> playerBlock = new HashMap<>();

    void openMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9, MENU_TITLE);

        Arrays.stream(CubeEffectType.values()).forEach(effectType ->
                inv.setItem(effectType.getSlot(), ItemStackUtils.createItemStack(effectType.getMaterial()
                , 1
                , effectType.getName()
                , effectType.getLore())));

        p.openInventory(inv);
    }
}
