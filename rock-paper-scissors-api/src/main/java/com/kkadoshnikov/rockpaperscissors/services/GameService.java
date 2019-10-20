package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.game.GameResultCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service which perform game and generate result.
 */
@Service
@RequiredArgsConstructor
public class GameService {

    private final StrategiesService strategiesService;
    private final GameResultCalculator calculator;
    private final SubscriberPlayEventService subscriberPlayEventService;

    /**
     * Play one game with a player.
     * @param playerId - Id of player.
     * @param playerItem - Player's choice.
     * @return GameResult.
     */
    public GameResult play(Integer playerId, Item playerItem) {
        Item appItem = strategiesService.getStrategy(playerId).choose(playerId);
        GameResult gameResult = calculator.calculate(playerItem, appItem);
        subscriberPlayEventService.afterGame(playerId, gameResult);
        return gameResult;
    }
}
