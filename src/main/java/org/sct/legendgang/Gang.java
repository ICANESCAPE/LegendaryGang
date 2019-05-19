package org.sct.legendgang;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.dto.SqlInfo;
import org.sct.legendgang.mysql.Sql;

public final class Gang extends JavaPlugin {

    @Getter private static Gang instance;

    @Getter private static GangApi api;

    @Getter private static Sql sql;

    @Override
    public void onEnable() {
        instance = this;
        sql = new Sql(SqlInfo.host, SqlInfo.port, SqlInfo.dbase, SqlInfo.user, SqlInfo.passcode, SqlInfo.table);
    }

    @Override
    public void onDisable() {

    }



}
