package me.rdev.effects;

import me.rdev.LootBoxEffects;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class CubeEffect {

    private Effect effect;

    public CubeEffect(CubeEffectType effectType, Location location, @Nullable Player player) {

        Class<? extends Effect> clazz = LootBoxEffects.getInstance().getEffectsManager().getEffects().get(effectType);

        if (clazz == null) {
            System.out.println(effectType.name() + " wasn't loaded, check your code!");
            return;
        }

        try {
            effect = clazz.getDeclaredConstructor(Location.class, Player.class).newInstance(location, player);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("Could not load effect " + clazz.getSimpleName());
        }
    }

    public void start() {
        if (effect != null) effect.start();
    }
}
