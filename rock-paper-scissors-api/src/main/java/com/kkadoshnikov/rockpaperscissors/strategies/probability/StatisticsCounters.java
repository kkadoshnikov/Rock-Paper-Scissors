package com.kkadoshnikov.rockpaperscissors.strategies.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

/**
 * Count user's statistic for SimpleStatisticsStrategy.
 */
public interface StatisticsCounters {

    /**
     * Return most likely Player's symbol.
     */
    Symbol getMostLikelyplayerSymbol(Integer playerId);

    /**
     * Get counters of (prevSymbol, currSymbol) pairs.
     * @param playerId - Player's Id.
     * @return counters.
     */
    Map<Pair<Symbol, Symbol>, Long> getCounters(Integer playerId);
}
