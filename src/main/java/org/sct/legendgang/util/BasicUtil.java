package org.sct.legendgang.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author SCT_Alchemy
 * @date 2019-05-17 20:04
 */

public class BasicUtil {

    public static String convert(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> convert(List<String> msg) {
        List<String> converted = new ArrayList<>();
        msg.forEach(key -> converted.add(convert(key)));
        return converted;
    }

}
