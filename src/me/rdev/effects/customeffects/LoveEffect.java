package me.rdev.effects.customeffects;

import me.rdev.effects.Effect;
import me.rdev.utils.MathUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LoveEffect extends Effect {

    //HEART
    private static final double HEART_STEP = 50;
    private static final double HEART_TMAX = 2200;
    private static final double HEART_SIZE = 0.05;

    private static final double HEART_OFFSET_Y = 1;
    private static final float HEART_COLOR_STRENGTH = 1;
    private static final Color HEART_COLOR = Color.RED;
    //HEART

    //CYCLE
    private static final double CYCLE_RADIUS = 0.2;
    private static final double CYCLE_HEIGHT = 0.5;
    private static final double CYCLE_STEP = Math.PI / 3;
    private double cycleIterations = 0;
    //CYCLE

    public LoveEffect(Location loc, Player player) {
        super(loc, player);
        delay = 3;
        totalTasks = 43;
    }


    @Override
    public void taskOnStart() {
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 1,0,0,0,0);
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
    }

    @Override
    public void runTask() {

        if (iterations == totalTasks) {
            location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location.clone().add(0, 1.2, 0), 8,0.3,0,0.3,1);
            location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        }

        cycleIterations += ((totalTasks/1.65) > iterations) ? Math.PI / 13 : -(Math.PI / 9);

        heartEffect();
        cycle(cycleIterations);

        location.getWorld().spawnParticle(Particle.REDSTONE
                , location.clone().add(0, 0.8, 0)
                , 32,0.25,0,0.25,0
                , new Particle.DustOptions(Color.WHITE, 1f));
    }

    private void heartEffect() {
        for (int t = 0; t <= HEART_TMAX; t += HEART_STEP) {

            double x = 16 * Math.pow(Math.sin(t), 3);
            double y = 13 * Math.cos(t) - 5 * Math.cos(2*t) - Math.cos(3*t) - Math.cos(4*t) + 20;

            Vector vec = new Vector(x * HEART_SIZE, y * HEART_SIZE + HEART_OFFSET_Y, 0);
            vec = MathUtils.rotateAroundAxisY(vec, -Math.atan2(location.getZ() - player.getLocation().getZ(), location.getX() - player.getLocation().getX()) + 52);

            Location loc = location.clone().add(vec);

            loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1,0,0,0,0, new Particle.DustOptions(HEART_COLOR, HEART_COLOR_STRENGTH));

        }
    }


    private void cycle(double t) {
        for(double step = 0; step <= 2 * Math.PI; step += CYCLE_STEP) {

            double x = CYCLE_RADIUS * (4 * Math.PI - t) * Math.cos(t + step);
            double y = CYCLE_HEIGHT * t;
            double z = CYCLE_RADIUS * (4 * Math.PI - t) * Math.sin(t + step);

            location.getWorld().spawnParticle(Particle.HEART, location.clone().add(x, y, z), 1,0,0,0,0);
        }
    }
}
