package com.kkadoshnikov.rockpaperscissors.algorithms.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Counters of (prevItem, currItem) pairs.
 */
public class ItemAfterItemCounters extends HashMap<Pair<Item, Item>, Long> {

    public ItemAfterItemCounters() {
        for (Item prevItem : Item.values()) {
            for (Item item : Item.values()) {
                put(Pair.of(prevItem, item), 0L);
            }
        }
    }

    public void increment(Item prevItem, Item currItem) {
        if (prevItem == null) {
            return;
        }
        compute(Pair.of(prevItem, currItem), (pair, counter) -> counter + 1);
    }

    public Optional<Item> getMaxCurr(Item prevItem) {
        return Stream.of(Item.values())
                .map(curr -> Pair.of(prevItem, curr))
                .max(Comparator.comparingLong(this::get))
                .map(Pair::getValue);
    }
}
