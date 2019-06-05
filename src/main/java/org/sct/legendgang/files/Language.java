package org.sct.legendgang.files;

import org.sct.legendgang.Gang;
import org.sct.legendgang.util.BasicUtil;

/**
 * @author SCT_Alchemy
 * @date 2019-05-17 20:04
 * 语言文件
 */
public class Language {

    public static String getLang(String path) {
        return BasicUtil.convert(Gang.getInstance().getConfig().getString(path));
    }

}
