package com.kkadoshnikov.rockpaperscissors.strategies.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
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
    private final Map<Integer, SymbolAfterSymbolCounters> playerCountersMap = new HashMap<>();
    private final Map<Integer, Symbol> playerLastSymbolMap = new HashMap<>();

    @Override
    public void afterGame(Integer playerId, GameResult gameResult) {
        playerCountersMap.putIfAbsent(playerId, new SymbolAfterSymbolCounters());
        Symbol lastSymbol = playerLastSymbolMap.put(playerId, gameResult.getPlayerSymbol());
        playerCountersMap.get(playerId).increment(lastSymbol, gameResult.getPlayerSymbol());
    }

    @Override
    public Symbol getMostLikelyplayerSymbol(Integer playerId) {
        Symbol lastSymbol = playerLastSymbolMap.getOrDefault(playerId, Symbol.PAPER);
        return Optional.ofNullable(playerCountersMap.get(playerId))
                .flatMap(counters -> counters.getMaxCurr(lastSymbol))
                .orElse(Symbol.SCISSORS);
    }

    @Override
    public Map<Pair<Symbol, Symbol>, Long> getCounters(Integer playerId) {
        return unmodifiableMap(playerCountersMap.getOrDefault(playerId, new SymbolAfterSymbolCounters()));
    }
}
