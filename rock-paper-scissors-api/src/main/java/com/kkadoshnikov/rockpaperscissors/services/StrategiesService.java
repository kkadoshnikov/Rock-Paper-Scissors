package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.strategies.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This service choice strategy which should be used.
 */
@Service
public class StrategiesService {

    @Autowired
    @Qualifier("arstechnicaStrategy")
    private Strategy arstechnica;
    @Autowired
    @Qualifier("simpleStatisticsStrategy")
    private Strategy simpleStatistics;
    @Autowired
    private GameHistoryService historyService;
    @Value("${simpleStatisticsStrategy.minCount}")
    private Integer minStatisticsCount;

    /**
     * Choose strategy for game with player.
     * @param playerId - player's Id.
     * @return strategy.
     */
    public Strategy getStrategy(Integer playerId) {
        if (historyService.getGameCount(playerId) < minStatisticsCount) {
            return arstechnica;
        }
        return simpleStatistics;
    }
}
