package com.kkadoshnikov.rockpaperscissors;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameResult {
    private final Item playersItem;
    private final Item appsItem;
    private final Result result;
}
