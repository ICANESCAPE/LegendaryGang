package org.sct.legendgang.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jexl3.*;

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

    /**
     * 将String转换为表达式并且进行计算
     *
     * @param formula String格式的表达式
     * @return 计算后的值
     */
    public static int calculate(String formula) {
        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(formula);
        Object evaluate = jexlExpression.evaluate(null);
        return (int) evaluate;
    }

}
