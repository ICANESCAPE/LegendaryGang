package org.sct.legendgang.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.sct.legendgang.Gang;
import org.sct.legendgang.cache.GangCache;
import org.sct.legendgang.dto.Union;
import org.sct.legendgang.enumeration.DataType;
import org.sct.legendgang.mysql.SqlConnect;

import java.util.List;
import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2019-05-17 20:08
 */

public class GangApi {

    private static SqlConnect sql = Gang.getSql();

    /**
     * 获取所有的帮派名称
     *
     * @return 所有帮派名称集合
     */
    public List<String> getAllGangsName() {
        return sql.getColumn(DataType.DISPALY);
    }

    /**
     * 获取工会会长
     *
     * @param gangName 帮派名字
     * @return 工会会长的玩家对象
     */
    public Player getOwner(String gangName) {
        return Bukkit.getPlayer((String) sql.getValue(gangName, DataType.OWNER));
    }

    /**
     * 对相关数据进行增加
     *
     * @param gangName 工会名字
     * @param type 修改的数据
     */
    public void addValue(String gangName, DataType type, int amount) {
        sql.edit(gangName, type, amount);
    }

    /**
     * 获取指定的数据
     *
     * @param gangName 工会名字
     * @param value 获取的数据
     * @return 结果
     */
    public Object getValue(String gangName, DataType value) {
        return sql.getValue(gangName, value);
    }

    /**
     * 判断这个工会是否存在
     *
     * @param gangName 工会名称
     * @return
     */
    public boolean hasData(String gangName) {
        return sql.hasData(gangName);
    }


    /**
     * 创建一个工会
     *
     * @param gangName 工会名称
     * @param owner 工会会长
     * @param initLevel 初始化等级
     * @param initMoney 初始化金钱
     */
    public void createGang(String gangName, String owner, int initLevel, int initMoney) {
        sql.insert(gangName, owner, initLevel, initMoney, Gang.getGangManager().calculateValue(initLevel, Bukkit.getPlayer(owner)));
    }

    /**
     * 将所有工会都加载到缓存Map中
     *
     * @return Map<公会名字, 工会></>
     */
    public static Map<String, Union> loadAllGangs() {
        return GangCache.loadAllGangs();
    }

}
