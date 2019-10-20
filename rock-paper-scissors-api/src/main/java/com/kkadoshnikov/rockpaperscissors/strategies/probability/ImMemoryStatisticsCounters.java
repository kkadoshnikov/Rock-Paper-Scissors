package com.kkadoshnikov.rockpaperscissors.strategies.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.services.PlayEventSubscriber;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.unmodifiableMap;

/**
 * In memory implementation. This class isn't thread save. It is assumed, that player plays games sequentially.
 */
@Component
public class ImMemoryStatisticsCounters implements PlayEventSubscriber, StatisticsCounters {
    private final Map<Integer, ItemAfterItemCounters> playerCountersMap = new HashMap<>();
    private final Map<Integer, Item> playerLastItemMap = new HashMap<>();

    @Override
    public void afterGame(Integer playerId, GameResult gameResult) {
        playerCountersMap.putIfAbsent(playerId, new ItemAfterItemCounters());
        Item lastItem = playerLastItemMap.put(playerId, gameResult.getPlayerItem());
        playerCountersMap.get(playerId).increment(lastItem, gameResult.getPlayerItem());
    }

    @Override
    public Item getMostLikelyplayerItem(Integer playerId) {
        Item lastItem = playerLastItemMap.getOrDefault(playerId, Item.PAPER);
        return Optional.ofNullable(playerCountersMap.get(playerId))
                .flatMap(counters -> counters.getMaxCurr(lastItem))
                .orElse(Item.SCISSORS);
    }

    @Override
    public Map<Pair<Item, Item>, Long> getCounters(Integer playerId) {
        return unmodifiableMap(playerCountersMap.getOrDefault(playerId, new ItemAfterItemCounters()));
    }
}
