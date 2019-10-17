package com.kkadoshnikov.rockpaperscissors.game;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import com.kkadoshnikov.rockpaperscissors.exceptions.ConfigurationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

import static com.kkadoshnikov.rockpaperscissors.enums.Item.PAPER;
import static com.kkadoshnikov.rockpaperscissors.enums.Item.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Item.SCISSORS;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.DRAW;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.LOSE;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.WIN;

/**
 * Calculate game result by userItem and appItem.
 */
@Service
public class GameResultCalculator {

    private Map<Item, Map<Item, Result>> playerAppResultMap = new EnumMap<>(Item.class);

    public GameResultCalculator() {
        configureMap();
        validateMapConfiguration();
    }

    /**
     * Calculate game result by userItem and appItem.
     */
    public GameResult calculate(Item playersItem, Item appItem) {
        Result result = playerAppResultMap.get(playersItem).get(appItem);
        return new GameResult(playersItem, appItem, result, LocalDateTime.now());
    }

    private void configureMap() {
        for (Item item : Item.values()) {
            playerAppResultMap.put(item, new EnumMap<>(Item.class));
        }

        putResult(PAPER, PAPER, DRAW);
        putResult(PAPER, ROCK, WIN);
        putResult(PAPER, SCISSORS, LOSE);
        putResult(ROCK, PAPER, LOSE);
        putResult(ROCK, ROCK, DRAW);
        putResult(ROCK, SCISSORS, WIN);
        putResult(SCISSORS, PAPER, WIN);
        putResult(SCISSORS, ROCK, LOSE);
        putResult(SCISSORS, SCISSORS, DRAW);
    }

    private void putResult(Item usersItem, Item appItem, Result result) {
        playerAppResultMap.get(usersItem).put(appItem, result);
    }

    /**
     * Validate that map is configured properly.
     */
    private void validateMapConfiguration() {
        if (playerAppResultMap.size() < Item.values().length) {
            throw new ConfigurationException("Game result calculator is wrong configured.");
        }
        playerAppResultMap.values().forEach(appResultMap -> {
            if (appResultMap.size() < Item.values().length) {
                throw new ConfigurationException("Game result calculator is wrong configured.");
            }
        });
    }
}
