package org.sct.legendgang.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.sct.legendgang.Gang;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.dto.Union;
import org.sct.legendgang.dto.UnionData;
import org.sct.legendgang.enumeration.DataType;
import org.sct.legendgang.event.GangDataUpdateEvent;
import org.sct.legendgang.exception.DataUpdateException;

/**
 * @author SCT_Alchemy
 * @date 2019-05-20 10:28
 */

public class GangDataUtil {

    private GangApi api = Gang.getApi();

    public int calculateValue(String gangName) {
        String formula = Gang.getInstance().getConfig().getString("GangSetting.value")
                .replace("%level%", (String) api.getValue(gangName, DataType.LEVEL))
                .replace("%ownerlevel%",  String.valueOf(api.getOwner(gangName).getLevel()));
        return BasicUtil.calculate(formula);
    }

    public int calculateValue(int level, Player owner) {
        String formula = Gang.getInstance().getConfig().getString("GangSetting.value")
                .replace("%level%", String.valueOf(level))
                .replace("%ownerlevel%",  String.valueOf(owner.getLevel()));
        return BasicUtil.calculate(formula);
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
    public Union setGangData(Union union, DataType type, Object amount) throws DataUpdateException{
        UnionData data = union.getData();
        switch (type) {
            case LEVEL:
                data.setLevel(data.getLevel() + (int) amount);
                break;
            case MONEY:
                data.setPoints(data.getPoints() + (int) amount);
                break;
            case VALUE:
                data.setValue(data.getValue() + (int) amount);
                break;
            case CONTRIBUTE:
                data.setContributes(data.getContributes() + (int) amount);
                break;
            case OWNER:
                union.getAdmins().setOwner((String) amount);
                break;
            case DISPALY:
                union.setDispaly(BasicUtil.convert((String) amount));
                break;
            default:
                throw new DataUpdateException(BasicUtil.convert("&c数据更新时发生未知错误"), "hahaha, surprise mother fucker");
        }
        union.setData(data);
        Bukkit.getPluginManager().callEvent(new GangDataUpdateEvent(union, type, amount));
        return union;
    }

}
