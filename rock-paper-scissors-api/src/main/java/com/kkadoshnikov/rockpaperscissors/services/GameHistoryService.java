package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.strategies.probability.StatisticsCounters;
import com.kkadoshnikov.rockpaperscissors.enums.Item;
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
        Map<Pair<Item, Item>, Long> itemAfterItemCounts = statisticsCounters.getCounters(playerId);
        Map<Pair<Item, Item>, Double> itemAfterItemProbability = calculateItemAfterItemProbability(itemAfterItemCounts);

        return new GameStatistic(results, itemAfterItemCounts, itemAfterItemProbability);
    }

    private Map<Result, Long> calculateResults(Integer playerId) {
        return userGamesHistory.getOrDefault(playerId, Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(GameResult::getResult, Collectors.counting()));
    }

    private Map<Pair<Item, Item>, Double> calculateItemAfterItemProbability(
            Map<Pair<Item, Item>, Long> itemAfterItemCounts
    ) {
        Map<Pair<Item, Item>, Double> result = new HashMap<>();
        for (Item prev : Item.values()) {
            long total = Stream.of(Item.values())
                    .mapToLong(cur -> itemAfterItemCounts.getOrDefault(Pair.of(prev, cur), 0L))
                    .sum();
            for (Item cur : Item.values()) {
                double probability = (double) itemAfterItemCounts.get(Pair.of(prev, cur)) / total;
                result.put(Pair.of(prev, cur), probability);
            }
        }
        return result;
    }
}
