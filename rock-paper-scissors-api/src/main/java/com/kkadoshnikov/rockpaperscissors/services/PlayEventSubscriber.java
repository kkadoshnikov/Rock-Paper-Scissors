package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.game.GameResult;

/**
 * Subscriber interface for play event.
 */
public interface PlayEventSubscriber {

    default String getName() {
        return getClass().getSimpleName();
    }

    void afterGame(Integer playerId, GameResult gameResult);
}
