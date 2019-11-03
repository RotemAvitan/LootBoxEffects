package me.rdev.effects;

import me.rdev.LootBoxEffects;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public abstract class Effect implements Runnable {

    protected Location location;
    protected Player player;
    protected int totalTasks = 10;
    protected int iterations = 0;
    protected long delay = 10L;
    private boolean done = false;

    public Effect(Location location, @Nullable Player player) {
        this.location = location;
        this.player = player;
    }

    public abstract void runTask();
    public abstract void taskOnStart();

    @Override
    public void run() {

        if (iterations > totalTasks) {
            done = true;
        }

        if (done) {
            return;
        }

        try {
            runTask();
        } catch (Exception ex) {
            done = true;
        }

        iterations++;
    }

    void start() {
        LootBoxEffects.getInstance().getEffectsManager().startEffect(this);
        taskOnStart();
    }

}
