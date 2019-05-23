package org.sct.legendgang.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.sct.legendgang.Gang;
import org.sct.legendgang.cache.GangCache;
import org.sct.legendgang.dto.Union;
import org.sct.legendgang.enumeration.DataType;
import org.sct.legendgang.exception.DataUpdateException;
import org.sct.legendgang.mysql.SqlConnect;
import org.sct.legendgang.util.GangUtil;

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
     * @param type     修改的数据
     */
    public void addValue(String gangName, DataType type, int amount) {
        sql.add(gangName, type, amount);
    }

    /**
     * 对相关数据进行修改
     *
     * @param gangName 工会名字
     * @param type     修改数据类型
     * @param amount   修改值
     */
    public void editValue(String gangName, DataType type, Object amount) {
        sql.edit(gangName, type, amount);
    }

    /**
     * 获取指定的数据
     *
     * @param gangName 工会名字
     * @param value    获取的数据
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
     * @param gangName  工会名称
     * @param owner     工会会长
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

    /**
     * 更新工会数据，调用此方法会触发GangDataUpdateEvent事件
     * 在修改数据的同时，数据将会在数据库中更新
     * 次方法会返回更新数据后的Union对象
     *
     * @param union 工会对象
     * @param type 更新数据类型
     * @param amount 更新数据值
     * @return 更新后的数据
     * @throws DataUpdateException motherfucker!
     */
    public Union setUnionData(Union union, DataType type, Object amount) throws DataUpdateException{
        return Gang.getGangManager().setGangData(union, type, amount);
    }

    /**
     * 给工会增加一名成员
     *
     * @param union 工会
     * @param player 新增成员
     * @return 修改数据后的工会对象
     */
    public Union addMember(Union union, Player player) {
        return GangUtil.addMember(union, player);
    }

}
