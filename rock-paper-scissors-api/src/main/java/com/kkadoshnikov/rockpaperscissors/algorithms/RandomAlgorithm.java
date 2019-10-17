package com.kkadoshnikov.rockpaperscissors.algorithms;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Random algorithm. It can be used in games with new users or in case of problems with load.
 */
@Service
public class RandomAlgorithm implements Algorithm {

    @Override
    public Item choose(Integer playerId) {
        return Item.values()[ThreadLocalRandom.current().nextInt(Item.values().length)];
    }
}
