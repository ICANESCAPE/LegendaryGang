package org.sct.legendgang.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.sct.legendgang.Gang;
import org.sct.legendgang.api.GangApi;
import org.sct.legendgang.dto.Union;
import org.sct.legendgang.event.GangDataUpdateEvent;

/**
 * @author SCT_Alchemy
 * @date 2019-05-22 20:42
 */

public class GangDataListener implements Listener {

    private static GangApi api = Gang.getApi();

    @EventHandler
    void onUpdate(GangDataUpdateEvent e) {
        Union union = e.getUnion();
        if (union == null) {
            return;
        }
        api.editValue(union.getDispaly(), e.getType(), e.getValue());
    }
}
