package com.kkadoshnikov.rockpaperscissors.algorithms.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Item;

/**
 * Count user's statistic for SimpleStatisticsAlgorithm.
 */
public interface StatisticsCounters {

    /**
     * Return most likely Player's item.
     */
    Item getMostLikelyplayerItem(Integer playerId);
}
