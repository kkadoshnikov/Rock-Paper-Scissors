package com.kkadoshnikov.rockpaperscissors.strategies.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

/**
 * Count user's statistic for SimpleStatisticsStrategy.
 */
public interface StatisticsCounters {

    /**
     * Return most likely Player's item.
     */
    Item getMostLikelyplayerItem(Integer playerId);

    /**
     * Get counters of (prevItem, currItem) pairs.
     * @param playerId - Player's Id.
     * @return counters.
     */
    Map<Pair<Item, Item>, Long> getCounters(Integer playerId);
}
