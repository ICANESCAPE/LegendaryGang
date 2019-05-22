package org.sct.legendgang.event;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.sct.legendgang.dto.Union;
import org.sct.legendgang.dto.UnionData;
import org.sct.legendgang.enumeration.DataType;

/**
 * @author SCT_Alchemy
 * @date 2019-05-22 20:34
 */

public class GangDataUpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Getter @Setter Union union;

    @Getter DataType type;

    @Getter Object value;

    /**
     * 构造函数
     *
     * @param union 工会对象
     * @param type 数据修改类型
     * @param value 数据修改值
     */
    public GangDataUpdateEvent(Union union, DataType type, Object value) {
        this.union = union;
        this.type = type;
        this.value = value;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
