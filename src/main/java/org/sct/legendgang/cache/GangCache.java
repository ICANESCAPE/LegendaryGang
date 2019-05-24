package org.sct.legendgang.cache;

import lombok.Getter;
import org.sct.legendgang.Gang;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.dto.Union;
import org.sct.legendgang.dto.UnionData;
import org.sct.legendgang.enumeration.DataType;
import org.sct.legendgang.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 19:55
 */

public class GangCache {

    @Getter private static Map<String, Union> cache = new HashMap<>();

    private static GangApi api = Gang.getApi();

    public static Map<String, Union> loadAllGangs() {
        cache.clear();
        loadGangToCache();
        return cache;
    }

    public static void loadGangToCache() {
        Union union = new Union();
        UnionData data = new UnionData();
        List<String> allGangsName = api.getAllGangsName();
        for (String key : allGangsName) {
            data.setContributes((int) api.getValue(key, DataType.CONTRIBUTE));
            data.setCoins((int) api.getValue(key, DataType.MONEY));
            data.setLevel((int) api.getValue(key, DataType.LEVEL));
            data.setValue((int) api.getValue(key, DataType.VALUE));
            union.setData(data);
            union.setDispaly(key);
            union.setMembers(StringUtil.getMembers(key));
            cache.put(key, union);
        }
    }
}
