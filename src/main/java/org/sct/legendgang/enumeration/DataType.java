package org.sct.legendgang.enumeration;

import lombok.Getter;

/**
 * @author alchemy
 */
public enum DataType {

    /**
     * 数据库中列的信息
     */
    DISPALY("name"),
    OWNER("owner"),
    LEVEL("level"),
    VALUE("value"),
    MONEY("money");

    @Getter String name;

    DataType(String column) {
        this.name = column;
    }
}
