package com.kkadoshnikov.rockpaperscissors.algorithms.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.services.PlayEventSubscriber;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * In memory implementation. This class isn't thread save. It is assumed, that player plays games sequentially.
 */
@Component
public class ImMemoryStatisticsCounters implements PlayEventSubscriber, StatisticsCounters {
    private final Map<Integer, Counters> playerCountersMap = new HashMap<>();
    private final Map<Integer, Item> playerLastItemMap = new HashMap<>();

    @Override
    public void afterGame(Integer playerId, GameResult gameResult) {
        playerCountersMap.putIfAbsent(playerId, new Counters());
        Item lastItem = playerLastItemMap.put(playerId, gameResult.getPlayersItem());
        playerCountersMap.get(playerId).increment(lastItem, gameResult.getPlayersItem());
    }

    @Override
    public Item getMostLikelyPlayersItem(Integer playerId) {
        Item lastItem = playerLastItemMap.getOrDefault(playerId, Item.PAPER);
        return Optional.ofNullable(playerCountersMap.get(playerId))
                .flatMap(counters -> counters.getMaxCurr(lastItem))
                .orElse(Item.SCISSORS);
    }

    /**
     * Counters of (prevItem, currItem) pairs.
     */
    private static class Counters extends HashMap<Pair<Item, Item>, Integer> {
        Counters() {
            for (Item prevItem : Item.values()) {
                for (Item item : Item.values()) {
                    put(Pair.of(prevItem, item), 0);
                }
            }
        }

        void increment(Item prevItem, Item currItem) {
            if (prevItem == null) {
                return;
            }
            compute(Pair.of(prevItem, currItem), (pair, counter) -> counter + 1);
        }

        Optional<Item> getMaxCurr(Item prevItem) {
            return Stream.of(Item.values())
                    .map(curr -> Pair.of(prevItem, curr))
                    .max(Comparator.comparingInt(this::get))
                    .map(Pair::getValue);
        }


    }
}
