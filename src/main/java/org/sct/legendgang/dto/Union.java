package org.sct.legendgang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-05-17 19:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Union {

    /**
     * 工会名字
     */
    String dispaly;
    /**
     * 工会的管理层
     */
    UnionAdmin admins;
    /**
     * 工会的信息数据，包含工会金币点券/工会等级等
     */
    UnionData data;
    /**
     * 工会每日任务
     */
    UnionMission missions;
    /**
     * 工会技能
     */
    UnionSkills skills;
    /**
     * 工会普通成员
     */
    List<Player> members;

}
