package me.rdev;

import lombok.Getter;
import me.rdev.effects.CubeEffectType;
import me.rdev.effects.EffectsManager;
import me.rdev.effects.RainbowManager;
import me.rdev.effects.customeffects.LoveEffect;
import me.rdev.effects.customeffects.RainbowEffect;
import me.rdev.menu.LootBoxMenuListener;
import me.rdev.menu.LootBoxMenuManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LootBoxEffects extends JavaPlugin {

    @Getter private LootBoxMenuManager lootBoxMenuManager;
    @Getter private RainbowManager rainbowManager;
    @Getter private EffectsManager effectsManager;
    @Getter private static LootBoxEffects instance;

    @Override
    public void onEnable() {
        instance = this;
        lootBoxMenuManager = new LootBoxMenuManager();
        rainbowManager = new RainbowManager();
        effectsManager = new EffectsManager();

        registerEffects();
        Bukkit.getPluginManager().registerEvents(new LootBoxMenuListener(), this);
        rainbowManager.runTaskTimer(this, 20, 2);
    }

    private void registerEffects() {
        effectsManager.registerEffect(CubeEffectType.LOVE_EFFECT, LoveEffect.class);
        effectsManager.registerEffect(CubeEffectType.RAINBOW_EFFECT, RainbowEffect.class);
    }
}
