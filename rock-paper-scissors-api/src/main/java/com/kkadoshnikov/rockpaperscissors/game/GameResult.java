package com.kkadoshnikov.rockpaperscissors.game;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Game result POJO class.
 */
@AllArgsConstructor
@Getter
public class GameResult {
    private final Symbol playerSymbol;
    private final Symbol appsSymbol;
    private final Result result;
    private final LocalDateTime gameDateTime;
}
