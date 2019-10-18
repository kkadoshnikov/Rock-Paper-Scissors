package com.kkadoshnikov.rockpaperscissors.game;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
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
    private final Map<Pair<Item, Item>, Long> itemAfterItemCounts;
    private final Map<Pair<Item, Item>, Double> itemAfterItemProbability;
}
