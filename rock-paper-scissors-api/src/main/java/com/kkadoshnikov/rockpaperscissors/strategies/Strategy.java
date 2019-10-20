package com.kkadoshnikov.rockpaperscissors.strategies;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;

/**
 * This is basic interface for strategies of choosing symbol.
 */
public interface Strategy {

    /**
     * Choose symbol. Strategy mustn't know user's choice.
     * @param playerId
     * @return symbol
     */
    Symbol choose(Integer playerId);
}
