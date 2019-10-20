package com.kkadoshnikov.rockpaperscissors.strategies.probability;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Counters of (prevSymbol, currSymbol) pairs.
 */
public class SymbolAfterSymbolCounters extends HashMap<Pair<Symbol, Symbol>, Long> {

    public SymbolAfterSymbolCounters() {
        for (Symbol prevSymbol : Symbol.values()) {
            for (Symbol symbol : Symbol.values()) {
                put(Pair.of(prevSymbol, symbol), 0L);
            }
        }
    }

    public void increment(Symbol prevSymbol, Symbol currSymbol) {
        if (prevSymbol == null) {
            return;
        }
        compute(Pair.of(prevSymbol, currSymbol), (pair, counter) -> counter + 1);
    }

    public Optional<Symbol> getMaxCurr(Symbol prevSymbol) {
        return Stream.of(Symbol.values())
                .map(curr -> Pair.of(prevSymbol, curr))
                .max(Comparator.comparingLong(this::get))
                .map(Pair::getValue);
    }
}
