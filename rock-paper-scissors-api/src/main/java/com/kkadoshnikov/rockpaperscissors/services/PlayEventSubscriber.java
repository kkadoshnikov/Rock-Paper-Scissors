package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.game.GameResult;

/**
 * Subscriber interface for play event.
 */
public interface PlayEventSubscriber {

    /**
     * Get subscriber's name.
     * @return name
     */
    default String getName() {
        return getClass().getSimpleName();
    }

    /**
     * Handle afterGame event.
     * @param playerId - game's player.
     * @param gameResult - game's result.
     */
    void afterGame(Integer playerId, GameResult gameResult);
}
