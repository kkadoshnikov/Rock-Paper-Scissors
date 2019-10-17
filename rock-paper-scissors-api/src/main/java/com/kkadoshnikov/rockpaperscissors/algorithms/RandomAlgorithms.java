package com.kkadoshnikov.rockpaperscissors.algorithms;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomAlgorithms implements Algorithm {

    @Override
    public Item generate(Integer playerId) {
        return Item.values()[ThreadLocalRandom.current().nextInt(Item.values().length)];
    }
}
