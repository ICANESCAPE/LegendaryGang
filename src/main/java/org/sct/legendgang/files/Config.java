package org.sct.legendgang.files;

import org.sct.legendgang.Gang;

/**
 * @author SCT_Alchemy
 * @date 2019-05-17 20:04
 * 配置文件
 */
public class Config {
    public static Object getObject(String path) {
        return Gang.getInstance().getConfig().get(path);
    }
}
