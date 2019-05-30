package org.sct.legendgang.cache;

import lombok.Getter;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import org.sct.legendgang.Gang;
import org.sct.legendgang.enumeration.ItemType;
import org.sct.legendgang.files.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2019-05-24 18:36
 * 将自定义物品加载到缓存中
 */

public class ItemCache {

    @Getter private static Map<ItemType, ItemStack> itemMap = new HashMap<>();

    private static YamlConfiguration config = Item.getItemConfig();

    private static List<ItemFlag> flag = new ArrayList<>();

    static {
        flag.add(ItemFlag.HIDE_UNBREAKABLE);
        flag.add(ItemFlag.HIDE_ATTRIBUTES);
        flag.add(ItemFlag.HIDE_DESTROYS);
        flag.add(ItemFlag.HIDE_POTION_EFFECTS);
    }

    public void loadItemToCache() {
        itemMap.put(ItemType.CREATEITEM, loadCreateItem());
        itemMap.put(ItemType.ADDATIONITEM, loadAddationItem());
    }

    private ItemStack loadCreateItem() {
        String path = ItemType.CREATEITEM.getPath();
        ItemStack item = Gang.getItemManager().buildItem(
                config.getString(path + ".display"),
                        config.getStringList(path + ".lore"),
                        config.getString(path + ".material"),
                        config.getInt(path + "id"),
                        config.getInt(path + "amount"),
                        flag,
                        config.getBoolean(path + "unbreak")
        );
        return item;
    }

    private ItemStack loadAddationItem() {
        String path = ItemType.ADDATIONITEM.getPath();
        ItemStack item = Gang.getItemManager().buildItem(
                config.getString(path + ".display"),
                config.getStringList(path + ".lore"),
                config.getString(path + ".material"),
                config.getInt(path + "id"),
                config.getInt(path + "amount"),
                flag,
                config.getBoolean(path + "unbreak")
        );
        return item;
    }


}
