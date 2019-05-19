package org.sct.legendgang.dto;

import org.bukkit.configuration.file.FileConfiguration;

import org.sct.legendgang.Gang;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 13:27
 */

public class SqlInfo {

    private static FileConfiguration config = Gang.getInstance().getConfig();

    public static String user = config.getString("Mysql.user");

    public static String passcode = config.getString("Mysql.passcode");

    public static String host = config.getString("Mysql.host");

    public static int port = config.getInt("Mysql.port");

    public static String table = config.getString("Mysql.table");

    public static String dbase = config.getString("Mysql.database");

}
