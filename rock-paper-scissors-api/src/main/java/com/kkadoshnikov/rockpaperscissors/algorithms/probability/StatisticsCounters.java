package com.kkadoshnikov.rockpaperscissors.algorithms.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.services.PlayEventSubscriber;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Count user's statistic for SimpleStatisticsAlgorithm.
 */
@Component
public class StatisticsCounters implements PlayEventSubscriber {
    private final Map<Integer, CountersByPreviousItem> playersCountersByPreviousItem = new HashMap<>();
    private final Map<Integer, Item> playerLastItemMap = new HashMap<>();

    @Override
    public void afterGame(Integer playerId, GameResult gameResult) {
        synchronized (playerId) {
            Item lastItem = playerLastItemMap.put(playerId, gameResult.getPlayersItem());
            playersCountersByPreviousItem.putIfAbsent(playerId, new CountersByPreviousItem());
            Counters countersForLastItem = playersCountersByPreviousItem.get(playerId).get(lastItem);
            countersForLastItem.compute(gameResult.getPlayersItem(), (userId, count) -> count + 1);
        }
    }

    /**
     * Return most likely Player's item.
     */
    public Item getMostLikelyPlayersItem(Integer playerId) {
        Item lastItem = playerLastItemMap.getOrDefault(playerId, Item.PAPER);
        return Optional.ofNullable(playersCountersByPreviousItem.get(playerId))
                .map(countersByPreviousItem -> countersByPreviousItem.get(lastItem))
                .flatMap(this::itemWithMaxCount)
                .orElse(Item.SCISSORS);
    }

    private Optional<Item> itemWithMaxCount(Counters counters) {
        return counters.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey);
    }

    private static class CountersByPreviousItem extends EnumMap<Item, Counters> {
        CountersByPreviousItem() {
            super(Item.class);
            for (Item item : Item.values()) {
                put(item, new Counters());
            }
        }
    }

    private static class Counters extends EnumMap<Item, Integer> {

        Counters() {
            super(Item.class);
            for (Item item : Item.values()) {
                put(item, 0);
            }
        }
    }
}
