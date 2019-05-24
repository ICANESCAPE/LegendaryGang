package org.sct.legendgang.enumeration;

import lombok.Getter;

import org.sct.legendgang.Gang;

/**
 * @author SCT_Alchemy
 * @date 2019-05-24 18:37
 */

public enum  ItemType {

    /**
     * 工会道具的枚举类型
     */
    CREATEITEM(Gang.getInstance().getConfig().getString("init"));

    @Getter String path;

    ItemType(String path) {
        this.path = path;
    }
}
