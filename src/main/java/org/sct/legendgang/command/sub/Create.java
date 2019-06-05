package org.sct.legendgang.command.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.sct.legendgang.Gang;
import org.sct.legendgang.cache.ItemCache;
import org.sct.legendgang.command.SubCommand;
import org.sct.legendgang.dto.sub.CommandType;
import org.sct.legendgang.dto.sub.ConfigType;
import org.sct.legendgang.dto.sub.LanguageType;
import org.sct.legendgang.enumeration.ItemType;
import org.sct.legendgang.files.Config;
import org.sct.legendgang.files.Language;
import org.sct.legendgang.util.BasicUtil;
import org.sct.legendgang.util.InventoryUtil;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 19:50
 */

public class Create implements SubCommand {

    private static InventoryUtil inventoryUtil = Gang.getInventoryUtil();

    /**
     * 指令/lg create 公会名字
     *
     * @param sender
     * @param args
     * @return
     */
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(BasicUtil.convert("&c只能以玩家身份输入指令"));
        }
        Player player = (Player) sender;
        if (args[0].equals(CommandType.create)) {
            if (inventoryUtil.hasItem((Player) sender, ItemCache.getItemMap().get(ItemType.CREATEITEM)) || inventoryUtil.hasItem((Player) sender, ItemCache.getItemMap().get(ItemType.ADDATIONITEM))) {
                Gang.getApi().createGang(BasicUtil.convert(args[1]), sender.getName(), (int) Config.getObject(ConfigType.initLevel), (int) Config.getObject(ConfigType.initMoney));
                player.sendMessage(Language.getLang(LanguageType.createMessage)
                        .replace("%player%", player.getDisplayName())
                        .replace("%union%", BasicUtil.convert(args[1]))
                );
            } else {
                player.sendMessage(Language.getLang(LanguageType.notEnough));
            }
        }
        return false;
    }

}
