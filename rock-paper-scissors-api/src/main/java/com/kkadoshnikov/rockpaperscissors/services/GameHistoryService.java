package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import com.kkadoshnikov.rockpaperscissors.strategies.probability.StatisticsCounters;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.game.GameStatistic;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Games history service.
 * This is initial version with in-memory storage.
 */
@Service
@RequiredArgsConstructor
public class GameHistoryService implements PlayEventSubscriber {

    //ToDo: add storage for game history instead of map.
    private final Map<Integer, List<GameResult>> userGamesHistory = new ConcurrentHashMap<>();
    private final StatisticsCounters statisticsCounters;

    @Override
    public void afterGame(Integer playerId, GameResult gameResult) {
        List<GameResult> results = userGamesHistory.putIfAbsent(playerId, new CopyOnWriteArrayList<>());
        if (results == null) {
            results = userGamesHistory.get(playerId);
        }
        results.add(gameResult);
    }

    /**
     * Get Player's games count.
     * @param playerId - player's Id.
     * @return Player's games count.
     */
    public Integer getGameCount(Integer playerId) {
        if (!userGamesHistory.containsKey(playerId)) {
            return 0;
        }
        return userGamesHistory.get(playerId).size();
    }

    /**
     * Get games history by Player's Id.
     */
    public List<GameResult> getHistoryByPlayerId(Integer playerId) {
        return Collections.unmodifiableList(userGamesHistory.getOrDefault(playerId, Collections.emptyList()));
    }

    public GameStatistic getStatistic(Integer playerId) {
        Map<Result, Long> results = calculateResults(playerId);
        Map<Pair<Symbol, Symbol>, Long> symbolAfterSymbolCounts = statisticsCounters.getCounters(playerId);
        Map<Pair<Symbol, Symbol>, Double> symbolAfterSymbolProbability = calculateSymbolAfterSymbolProbability(
                symbolAfterSymbolCounts);

        return new GameStatistic(results, symbolAfterSymbolCounts, symbolAfterSymbolProbability);
    }

    private Map<Result, Long> calculateResults(Integer playerId) {
        return userGamesHistory.getOrDefault(playerId, Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(GameResult::getResult, Collectors.counting()));
    }

    private Map<Pair<Symbol, Symbol>, Double> calculateSymbolAfterSymbolProbability(
            Map<Pair<Symbol, Symbol>, Long> symbolAfterSymbolCounts
    ) {
        Map<Pair<Symbol, Symbol>, Double> result = new HashMap<>();
        for (Symbol prev : Symbol.values()) {
            long total = Stream.of(Symbol.values())
                    .mapToLong(cur -> symbolAfterSymbolCounts.getOrDefault(Pair.of(prev, cur), 0L))
                    .sum();
            for (Symbol cur : Symbol.values()) {
                double probability = (double) symbolAfterSymbolCounts.get(Pair.of(prev, cur)) / total;
                result.put(Pair.of(prev, cur), probability);
            }
        }
        return result;
    }
}
