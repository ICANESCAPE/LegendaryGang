package org.sct.legendgang.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.sct.legendgang.dto.Union;
import org.sct.legendgang.enumeration.DataType;
import org.sct.legendgang.event.GangDataUpdateEvent;


import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 20:01
 */

public class GangUtil {

    public static Union addMember(Union union, Player player) {
        List<Player> players = union.getMembers();
        players.add(player);
        union.setMembers(players);
        Bukkit.getPluginManager().callEvent(new GangDataUpdateEvent(union, DataType.MEMBER, StringUtil.convertToString(players)));
        return union;
    }
}
