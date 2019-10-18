package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This service choice algorithm which should be used.
 */
@Service
public class AlgorithmsService {

    @Autowired
    @Qualifier("arstechnicaAlgorithm")
    private Algorithm arstechnica;
    @Autowired
    @Qualifier("simpleStatisticsAlgorithm")
    private Algorithm simpleStatistics;
    @Autowired
    private GameHistoryService historyService;
    @Value("${simpleStatisticsAlgorithm.minCount}")
    private Integer minStatisticsCount;

    /**
     * Choose algorithm for game with player.
     * @param playerId - player's Id.
     * @return algorithm.
     */
    public Algorithm getAlgorithm(Integer playerId) {
        if (historyService.getGameCount(playerId) < minStatisticsCount) {
            return arstechnica;
        }
        return simpleStatistics;
    }
}
