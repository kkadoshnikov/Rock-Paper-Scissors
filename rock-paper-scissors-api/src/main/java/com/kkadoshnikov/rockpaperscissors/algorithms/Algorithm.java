package com.kkadoshnikov.rockpaperscissors.algorithms;

import com.kkadoshnikov.rockpaperscissors.enums.Item;

/**
 * This is basic interface for algorithms of choosing item.
 */
public interface Algorithm {

    /**
     * Choose item. Algorithm mustn't know user's choice.
     * @param playerId
     * @return item
     */
    Item choose(Integer playerId);
}
