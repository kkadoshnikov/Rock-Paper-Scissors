package com.kkadoshnikov.rockpaperscissors.strategies.probability;

import com.kkadoshnikov.rockpaperscissors.strategies.Strategy;
import com.kkadoshnikov.rockpaperscissors.enums.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * This strategy is based on 'Markov chain'. The main idea is:
 * Probability of next user item depends on the current state (the last game result). E.g.:
 *
 * If during last game user chose scissors than with 20% probability he will choose scissors, with 35% rock
 * and with 45% paper.
 *
 * Strategy chooses item which bits user's the most likely choice.
 */
@Service
@RequiredArgsConstructor
public class SimpleStatisticsStrategy implements Strategy {

    private final StatisticsCounters statisticsCounters;

    @Override
    public Item choose(Integer playerId) {
        Item mostLikelyplayerItem = statisticsCounters.getMostLikelyplayerItem(playerId);
        return mostLikelyplayerItem.beatenBy();
    }
}
