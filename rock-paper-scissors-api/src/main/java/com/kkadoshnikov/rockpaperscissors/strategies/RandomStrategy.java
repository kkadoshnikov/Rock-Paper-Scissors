package com.kkadoshnikov.rockpaperscissors.strategies;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Choose next symbol using random. It can be used in games with new users or in case of problems with load.
 */
@Service
public class RandomStrategy implements Strategy {

    @Override
    public Symbol choose(Integer playerId) {
        return Symbol.values()[ThreadLocalRandom.current().nextInt(Symbol.values().length)];
    }
}
