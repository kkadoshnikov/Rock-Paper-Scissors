package com.kkadoshnikov.rockpaperscissors.strategies;

import com.kkadoshnikov.rockpaperscissors.enums.Item;

/**
 * This is basic interface for strategies of choosing item.
 */
public interface Strategy {

    /**
     * Choose item. Strategy mustn't know user's choice.
     * @param playerId
     * @return item
     */
    Item choose(Integer playerId);
}
