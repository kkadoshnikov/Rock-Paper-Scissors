package com.kkadoshnikov.rockpaperscissors.algorithms.probability;

import com.kkadoshnikov.rockpaperscissors.algorithms.Algorithm;
import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.exceptions.ConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

import static com.kkadoshnikov.rockpaperscissors.enums.Item.PAPER;
import static com.kkadoshnikov.rockpaperscissors.enums.Item.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Item.SCISSORS;

/**
 * This algorithm is based on 'Markov chain'. The main idea is:
 * Probability of next user item depends on the current state (the last game result). E.g.:
 *
 * If during last game user chose scissors than with 20% probability he will choose scissors, with 35% rock
 * and with 45% paper.
 *
 * Algorithm chooses item which bits user's the most likely choice.
 */
@Service
@RequiredArgsConstructor
public class SimpleStatisticsAlgorithm implements Algorithm {

    private final StatisticsCounters statisticsCounters;
    private final Map<Item, Item> choiceByPlayersItem;

    /**
     * Initialize method.
     */
    @PostConstruct
    public void init() {
        choiceByPlayersItem.put(SCISSORS, ROCK);
        choiceByPlayersItem.put(ROCK, PAPER);
        choiceByPlayersItem.put(PAPER, SCISSORS);

        if (choiceByPlayersItem.size() < Item.values().length) {
            throw new ConfigurationException("choiceByPlayersItem  is wrong configured.");
        }
    }

    @Override
    public Item choose(Integer playerId) {
        Item mostLikelyPlayersItem = statisticsCounters.getMostLikelyPlayersItem(playerId);
        return choiceByPlayersItem.get(mostLikelyPlayersItem);
    }
}
