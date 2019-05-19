package org.sct.legendgang.cache;

import lombok.Getter;
import org.sct.legendgang.Gang;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.dto.Union;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 19:55
 */

public class GangCache {

    @Getter private static Map<String, Union> cache = new HashMap<>();

    public static Map<String, Union> loadAllGangs() {
        cache.clear();
        return cache;
    }
}
