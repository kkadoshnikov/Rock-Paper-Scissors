package com.kkadoshnikov.rockpaperscissors.game;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

/**
 * Game statistic POJO class.
 */
@AllArgsConstructor
@Getter
public class GameStatistic {

    private final Map<Result, Long> results;
    private final Map<Pair<Symbol, Symbol>, Long> symbolAfterSymbolCounts;
    private final Map<Pair<Symbol, Symbol>, Double> symbolAfterSymbolProbability;
}
