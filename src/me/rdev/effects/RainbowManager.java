package me.rdev.effects;

import org.bukkit.Color;
import org.bukkit.scheduler.BukkitRunnable;

public class RainbowManager extends BukkitRunnable {

    private static final int RAINBOW_COLOR_SPEED = 3;
    private int rb = 0;

    @Override
    public void run() {
        rb += RAINBOW_COLOR_SPEED;
        rb %= 360;
    }

    public Color getRainbowColor() {
        java.awt.Color rgb = java.awt.Color.getHSBColor(rb / 360F, 1.0F, 1.0F);
        return Color.fromRGB(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
    }

}
