package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.game.GameResultCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final AlgorithmsService algorithmsService;
    private final GameResultCalculator calculator;
    private final SubscriberPlayEventService subscriberPlayEventService;

    public GameResult play(Integer playerId, Item playersItem) {
        Item appItem = algorithmsService.getAlgorithm(playerId).choose(playerId);
        GameResult gameResult = calculator.calculate(playersItem, appItem);
        subscriberPlayEventService.afterGame(playerId, gameResult);
        return gameResult;
    }
}
