package org.sct.legendgang.util;

import org.bukkit.entity.Player;
import org.sct.legendgang.Gang;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.enumeration.DataType;

/**
 * @author SCT_Alchemy
 * @date 2019-05-20 10:28
 */

public class GangDataUtil {

    private GangApi api = Gang.getApi();

    public int calculateValue(String gangName) {
        String formula = Gang.getInstance().getConfig().getString("GangSetting.value")
                .replace("%level%", (String) api.getValue(gangName, DataType.LEVEL))
                .replace("%ownerlevel%",  String.valueOf(api.getOwner(gangName).getLevel()));
        return BasicUtil.calculate(formula);
    }

    public int calculateValue(int level, Player owner) {
        String formula = Gang.getInstance().getConfig().getString("GangSetting.value")
                .replace("%level%", String.valueOf(level))
                .replace("%ownerlevel%",  String.valueOf(owner.getLevel()));
        return BasicUtil.calculate(formula);
    }


}
