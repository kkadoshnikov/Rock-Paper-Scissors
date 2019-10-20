package com.kkadoshnikov.rockpaperscissors.game;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import com.kkadoshnikov.rockpaperscissors.exceptions.ConfigurationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.PAPER;
import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.SCISSORS;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.DRAW;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.LOSE;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.WIN;

/**
 * Calculate game result by userSymbol and appSymbol.
 */
@Service
public class GameResultCalculator {

    private Map<Symbol, Map<Symbol, Result>> playerAppResultMap = new EnumMap<>(Symbol.class);

    public GameResultCalculator() {
        configureMap();
        validateMapConfiguration();
    }

    /**
     * Calculate game result by userSymbol and appSymbol.
     */
    public GameResult calculate(Symbol playerSymbol, Symbol appSymbol) {
        Result result = playerAppResultMap.get(playerSymbol).get(appSymbol);
        return new GameResult(playerSymbol, appSymbol, result, LocalDateTime.now());
    }

    private void configureMap() {
        for (Symbol symbol : Symbol.values()) {
            playerAppResultMap.put(symbol, new EnumMap<>(Symbol.class));
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

    private void putResult(Symbol usersSymbol, Symbol appSymbol, Result result) {
        playerAppResultMap.get(usersSymbol).put(appSymbol, result);
    }

    /**
     * Validate that map is configured properly.
     */
    private void validateMapConfiguration() {
        if (playerAppResultMap.size() < Symbol.values().length) {
            throw new ConfigurationException("Game result calculator is wrong configured.");
        }
        playerAppResultMap.values().forEach(appResultMap -> {
            if (appResultMap.size() < Symbol.values().length) {
                throw new ConfigurationException("Game result calculator is wrong configured.");
            }
        });
    }
}
