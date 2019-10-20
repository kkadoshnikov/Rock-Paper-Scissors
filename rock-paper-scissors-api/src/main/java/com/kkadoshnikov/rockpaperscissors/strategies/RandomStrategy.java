package com.kkadoshnikov.rockpaperscissors.strategies;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Choose next item using random. It can be used in games with new users or in case of problems with load.
 */
@Service
public class RandomStrategy implements Strategy {

    @Override
    public Item choose(Integer playerId) {
        return Item.values()[ThreadLocalRandom.current().nextInt(Item.values().length)];
    }
}
