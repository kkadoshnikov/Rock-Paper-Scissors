package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.GameResult;
import com.kkadoshnikov.rockpaperscissors.GameResultCalculator;
import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final Algorithm itemGenerator;
    private final GameResultCalculator calculator;

    public GameResult play(Integer playerId, Item playersItem) {
        Item appItem = itemGenerator.generate(playerId);

        return calculator.calculate(playersItem, appItem);
    }


}
