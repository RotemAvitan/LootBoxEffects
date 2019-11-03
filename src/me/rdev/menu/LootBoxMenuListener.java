package me.rdev.menu;

import me.rdev.LootBoxEffects;
import me.rdev.effects.CubeEffect;
import me.rdev.effects.CubeEffectType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LootBoxMenuListener implements Listener {

    //Called whenever player click on END_PORTAL_FRAME
    @EventHandler
    public void openEffectsMenu(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block clickedBlock = e.getClickedBlock();

        if (clickedBlock == null) {
            return;
        }

        if (clickedBlock.getType() != Material.END_PORTAL_FRAME) {
            return;
        }

        Player p = e.getPlayer();

        LootBoxEffects.getInstance().getLootBoxMenuManager().openMenu(p);
        LootBoxEffects.getInstance().getLootBoxMenuManager().getPlayerBlock().put(p.getUniqueId(), clickedBlock.getLocation());
    }

    //Menu click, choose effect!
    @EventHandler
    public void menuClick(InventoryClickEvent e) {

        if (!(e.getView().getTitle().equals(LootBoxMenuManager.MENU_TITLE))) {
            return;
        }

        if(e.getCurrentItem() == null) {
            return;
        }

        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        Location cube = LootBoxEffects.getInstance().getLootBoxMenuManager().getPlayerBlock().get(player.getUniqueId());

        //should not happen!
        if (cube == null) {
            player.closeInventory();
            return;
        }

        for (CubeEffectType cubeEffectType : CubeEffectType.values()) {
            if (!(cubeEffectType.getSlot() == e.getSlot())) continue;

            player.closeInventory();
            new CubeEffect(cubeEffectType, cube.add(0.5, 0, 0.5), player).start();
            break;
        }

        LootBoxEffects.getInstance().getLootBoxMenuManager().getPlayerBlock().remove(player.getUniqueId());
    }

    //In order to prevent memory leak
    @EventHandler
    public void listRemove(PlayerQuitEvent e) {
        LootBoxEffects.getInstance().getLootBoxMenuManager().getPlayerBlock().remove(e.getPlayer().getUniqueId());
    }

    //In order to prevent memory leak
    @EventHandler
    public void listRemove(InventoryCloseEvent e) {
        if (e.getView().getTitle().equals(LootBoxMenuManager.MENU_TITLE))
            LootBoxEffects.getInstance().getLootBoxMenuManager().getPlayerBlock().remove(e.getPlayer().getUniqueId());
    }
}
