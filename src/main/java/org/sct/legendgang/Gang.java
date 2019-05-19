package org.sct.legendgang;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.command.CommandHandle;
import org.sct.legendgang.dto.SqlInfo;
import org.sct.legendgang.mysql.SqlConnect;

public final class Gang extends JavaPlugin {

        @Getter private static Gang instance;

        @Getter private static GangApi api;

    @Getter private static SqlConnect sql;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§e[传奇公会]§1传奇公会已加载！");
        instance = this;
        sql = new SqlConnect(SqlInfo.host, SqlInfo.port, SqlInfo.dbase, SqlInfo.user, SqlInfo.passcode, SqlInfo.table);
        api = new GangApi();
        Bukkit.getPluginCommand("lg").setExecutor(new CommandHandle());

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§e[传奇公会]§c传奇公会已卸载！");
    }

}
