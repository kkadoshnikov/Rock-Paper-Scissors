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
    @Qualifier("randomAlgorithm")
    private Algorithm random;
    @Autowired
    @Qualifier("simpleStatisticsAlgorithm")
    private Algorithm simpleStatistics;
    @Autowired
    private GameHistoryService historyService;
    @Value("${simpleStatisticsAlgorithm.minCount}")
    private Integer minStatisticsCount;

    public Algorithm getAlgorithm(Integer playerId) {
        if (historyService.getGameCount(playerId) < minStatisticsCount) {
            return random;
        }
        return simpleStatistics;
    }
}