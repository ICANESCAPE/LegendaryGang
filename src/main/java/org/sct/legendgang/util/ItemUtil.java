package org.sct.legendgang.util;

import lombok.NoArgsConstructor;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-05-24 18:24
 */

@NoArgsConstructor
public class ItemUtil {

    public ItemStack buildItem(String dispaly, List<String> lore, String material, int data, int amount, List<ItemFlag> flags, boolean unbreak) {
        ItemStack item = new ItemStack(Material.getMaterial(material), amount, (short) data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(BasicUtil.convert(dispaly));
        meta.setLore(BasicUtil.convert(lore));
        meta.setUnbreakable(unbreak);
        for (ItemFlag flag : flags) {
            meta.getItemFlags().add(flag);
        }
        item.setItemMeta(meta);
        return item;
    }


}
