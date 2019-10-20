package com.kkadoshnikov.rockpaperscissors.strategies;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.services.PlayEventSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.kkadoshnikov.rockpaperscissors.enums.Result.LOSE;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.WIN;

/**
 * Implementation of strategy offered by arstechnica website.
 * https://arstechnica.com/science/2014/05/win-at-rock-paper-scissors-by-knowing-thy-opponent/
 *
 * Therefore, this is the best way to win at rock-paper-scissors: if you lose the first round, switch to the thing
 * that beats the thing your opponent just played. If you win, don't keep playing the same thing, but instead switch
 * to the thing that would beat the thing that you just played. In other words, play the hand your losing opponent just
 * played. To wit: you win a round with rock against someone else's scissors. They are about to switch to paper. You
 * should switch to scissors.
 *
 * This class isn't thread save. It is assumed, that player plays games sequentially.
 */
@Service
@RequiredArgsConstructor
public class ArstechnicaStrategy implements Strategy, PlayEventSubscriber {

    private Map<Integer, GameResult> lastResultMap = new HashMap<>();
    private final RandomStrategy randomStrategy;

    @Override
    public Item choose(Integer playerId) {
        GameResult lastResult = lastResultMap.get(playerId);

        if (lastResult == null) {
            return randomStrategy.choose(playerId);
        }

        // If player win the first round, switch to the thing that beats his thing.
        if (lastResult.getResult().equals(WIN)) {
            return lastResult.getPlayerItem().beatenBy();
        }

        // If player lose, don't keep playing the same thing, but instead switch to the thing that would beat the thing
        // that app just played.
        if (lastResult.getResult().equals(LOSE)) {
            return lastResult.getAppsItem().beatenBy();
        }

        // There are no rules for a draw, so use random strategy.
        return randomStrategy.choose(playerId);
    }

    @Override
    public void afterGame(Integer playerId, GameResult gameResult) {
        lastResultMap.put(playerId, gameResult);
    }
}
