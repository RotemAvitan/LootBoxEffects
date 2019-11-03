package me.rdev.effects.customeffects;

import me.rdev.LootBoxEffects;
import me.rdev.effects.Effect;
import me.rdev.utils.ItemStackUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Color.*;

public class RainbowEffect extends Effect {

    private static final double RAINBOW_RADIUS = 2.5;
    private static final Color[] RAINBOW_COLORS = new Color[] { PURPLE, BLUE, GREEN, YELLOW, ORANGE, RED };
    private static final float HEART_COLOR_STRENGTH = 1.5f;
    private static final String BASE64_POT_TEXTURE =
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJ" +
            "lcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UyODVjNWM4NTU5OTQzN2" +
            "FjNDJlMjdmNDkyMWM4MDVhZWQxZGM1Nzc0Zjk1MmEyN2VhN2U4YzlhY" +
            "mU2ZSJ9fX0=";
    private Entity goldPot;
    private double potHeight = 0;

    public RainbowEffect(Location loc, Player player) {
        super(loc, player);
        delay = 3;
        totalTasks = 43;
    }

    @Override
    public void taskOnStart() {
        goldPot = spawnPot();
    }

    @Override
    public void runTask() {

        if (iterations == totalTasks) {
            if (goldPot != null && goldPot.isValid()) goldPot.remove();
        }

        if (potHeight <= 0.11) {
            if (goldPot != null)
            goldPot.teleport(goldPot.getLocation().add(0, potHeight, 0));
            potHeight += 0.01;
        }

        int stripesCount = 0;

        for (double height = 0; stripesCount < 6; height +=0.225) {
            rainbowStripe(location.clone().add(0, height,0), RAINBOW_COLORS[stripesCount]);
            stripesCount++;
        }

        location.getWorld().spawnParticle(Particle.REDSTONE
                , location.clone().add(0, 0.75, 0)
                , 32,0.25,0,0.25,0
                , new Particle.DustOptions(LootBoxEffects.getInstance().getRainbowManager().getRainbowColor(), 1f));

    }


    private void rainbowStripe(Location displayHeight, Color color) {
        for (double t = 0; t <= Math.PI; t+=0.15) {

            double x = Math.cos(t) * RAINBOW_RADIUS;
            double y = Math.sin(t) * RAINBOW_RADIUS;

            Location loc = displayHeight.clone().add(x, y, 0);

            location.getWorld().spawnParticle(Particle.REDSTONE
                    , loc
                    , 1,0,0,0,0
                    , new Particle.DustOptions(color, HEART_COLOR_STRENGTH));
        }
    }

    private Entity spawnPot() {
        if (location.getWorld() == null) return null;
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, -1.3, 0), EntityType.ARMOR_STAND);

        as.setGravity(false);
        as.setVisible(false);
        ItemStack head = ItemStackUtils.getSkull(BASE64_POT_TEXTURE);
        as.setHelmet(head);

        return as;
    }

}
