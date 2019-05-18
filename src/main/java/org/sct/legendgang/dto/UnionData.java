package org.sct.legendgang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SCT_Alchemy
 * @date 2019-05-17 19:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnionData {

    /**
     * 工会总战斗力，排行榜排名依据
     */
    int value;
    /**
     * 工会等级
     */
    int level;
    /**
     * 工会金币
     */
    int coins;
    /**
     * 工会点券
     */
    int points;
    /**
     * 工会贡献
     */
    int contributes;

}

