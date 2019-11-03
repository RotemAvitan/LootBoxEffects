package me.rdev.effects;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import me.rdev.LootBoxEffects;
import org.bukkit.Bukkit;

public class EffectsManager {

    @Getter private final BiMap<CubeEffectType, Class<? extends Effect>> effects = HashBiMap.create();

    public void registerEffect(CubeEffectType cubeEffect, Class<? extends Effect> clazz) {

        if (effects.containsValue(clazz)) {
            System.out.println("The effect " + clazz.getSimpleName() + " is already registered!");
            return;
        }
        effects.put(cubeEffect, clazz);
    }

    /**
     * Used in order to initialize the runnable!
     * @param effect The object of the effect.
     */
    void startEffect(Effect effect) {
        Preconditions.checkNotNull(effect, "The effect is null!");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(LootBoxEffects.getInstance(), effect, 10, effect.delay);
    }

}
