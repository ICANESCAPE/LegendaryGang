package org.sct.legendgang;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.legendgang.api.GangApi;

public final class Gang extends JavaPlugin {

    @Getter private static Gang instance;

    @Getter private static GangApi api;

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {

    }

}
