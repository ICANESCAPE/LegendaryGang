package org.sct.legendgang.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.sct.legendgang.Gang;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.enumeration.DataType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-05-22 19:23
 */

public class StringUtil {

    private static GangApi api = Gang.getApi();

    public static List<String> getPlayers(String gang) {
        List<String> players = new ArrayList<>();
        String members = (String) api.getValue(gang, DataType.MEMBER);
        for (String key : members.split(",")) {
            players.add(key);
        }
        return players;
    }

    public static List<Player> getMembers(String gang) {
        List<Player> players = new ArrayList<>();
        for (String key : getPlayers(gang)) {
            players.add(Bukkit.getPlayer(key));
        }
        return players;
    }

    public static String converToString(List<String> players) {
        StringBuilder builder = new StringBuilder();
        for (String key : players) {
            builder.append(key + ",");
        }
        return builder.toString();
    }

    public static String convertToString(List<Player> players) {
        StringBuilder builder = new StringBuilder();
        for (Player player : players) {
            builder.append(player.getDisplayName() + ",");
        }
        return builder.toString();
    }


}
