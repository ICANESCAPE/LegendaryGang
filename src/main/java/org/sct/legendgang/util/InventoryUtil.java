package org.sct.legendgang.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.sct.legendgang.Gang;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-05-30 16:13
 */

public class InventoryUtil {

    /**
     * 判断玩家是否拥有某个物品，如果物品被绑定也会视为拥有
     *
     * @param player 玩家
     * @param item 物品
     * @return 有/无
     */
    public boolean hasItem(Player player, ItemStack item) {
        String rule = Gang.getInstance().getConfig().getString("GangSetting.RuleLore");
        if (player.getInventory().contains(item)) {
            return true;
        }
        ItemStack copy = item.clone();
        copy = Gang.getItemManager().addLore(copy, rule);
        return isEqual(item, copy, rule);
    }

    public boolean isEqual(ItemStack item1, ItemStack item2, String rule) {
        List<String> list1 = item1.getItemMeta().getLore();
        List<String> list2 = item2.getItemMeta().getLore();
        if (list1 == null || list2 == null) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (rule != null && rule.equals(list1.get(i))) {
                continue;
            }
            if (!list2.contains(list1.get(i))) {
                return false;
            }
        }
        return true;
    }

}
