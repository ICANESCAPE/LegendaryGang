package org.sct.legendgang.enumeration;

import lombok.Getter;

import org.sct.legendgang.Gang;
import org.sct.legendgang.files.Item;

/**
 * @author SCT_Alchemy
 * @date 2019-05-24 18:37
 */

public enum  ItemType {



    /**
     * 工会道具的枚举类型
     */
    CREATEITEM(Item.getItemConfig().getString("CreateItem")),
    ADDATIONITEM(Item.getItemConfig().getString("AddationItem"));


    @Getter String path;

    ItemType(String path) {
        this.path = path;
    }
}
