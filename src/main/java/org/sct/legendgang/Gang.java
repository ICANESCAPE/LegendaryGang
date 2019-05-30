package org.sct.legendgang;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.cache.ItemCache;
import org.sct.legendgang.command.CommandHandle;
import org.sct.legendgang.dto.sub.SqlInfo;
import org.sct.legendgang.files.Item;
import org.sct.legendgang.mysql.SqlConnect;
import org.sct.legendgang.util.GangDataUtil;
import org.sct.legendgang.util.ItemUtil;

/**
 * @author alchemy, qnickx
 * @data 2019/5/20/10:19:44
 */
public final class Gang extends JavaPlugin {

    @Getter private static Gang instance;

    @Getter private static GangApi api;

    @Getter private static SqlConnect sql;

    @Getter private static GangDataUtil gangManager;

    @Getter private static ItemUtil itemManager;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§e[传奇公会] §a传奇公会已加载！");
        instance = this;
        sql = new SqlConnect(SqlInfo.host, SqlInfo.port, SqlInfo.dbase, SqlInfo.user, SqlInfo.passcode, SqlInfo.table);
        api = new GangApi();
        gangManager = new GangDataUtil();
        itemManager = new ItemUtil();
        Item.loadYml();
        Bukkit.getPluginCommand("lg").setExecutor(new CommandHandle());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§e[传奇公会]§c传奇公会已卸载！");
    }

}
