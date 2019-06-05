package org.sct.legendgang.files;

import lombok.Getter;

import org.bukkit.configuration.file.YamlConfiguration;

import org.sct.legendgang.Gang;


import java.io.File;

/**
 * @author SCT_Alchemy
 * @date 2019-05-30 15:14
 */

public class Item {

    private static File file;
    @Getter private static YamlConfiguration itemConfig;

    public static void loadYml() {
        file = new File(Gang.getInstance().getDataFolder(), "item.yml");
        itemConfig = YamlConfiguration.loadConfiguration(file);
        Gang.getItemManager().getItemCache().loadItemToCache();
    }

}
