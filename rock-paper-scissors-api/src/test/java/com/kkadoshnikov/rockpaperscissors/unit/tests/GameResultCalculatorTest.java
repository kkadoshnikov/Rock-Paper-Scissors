package com.kkadoshnikov.rockpaperscissors.unit.tests;

import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.game.GameResultCalculator;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.PAPER;
import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.SCISSORS;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.DRAW;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.LOSE;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.WIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for GameResultCalculator class.
 */
public class GameResultCalculatorTest {

    @Test
    public void rockRockTest() {
        BaseGameCalculatorTest
                .playersSymbol(ROCK)
                .appSymbol(ROCK)
                .expectedResult(DRAW)
                .test();
    }

    @Test
    public void rockPaperTest() {
        BaseGameCalculatorTest
                .playersSymbol(ROCK)
                .appSymbol(PAPER)
                .expectedResult(LOSE)
                .test();
    }

    @Test
    public void rockScissorsTest() {
        BaseGameCalculatorTest
                .playersSymbol(ROCK)
                .appSymbol(SCISSORS)
                .expectedResult(WIN)
                .test();
    }

    @Test
    public void paperRockTest() {
        BaseGameCalculatorTest
                .playersSymbol(PAPER)
                .appSymbol(ROCK)
                .expectedResult(WIN)
                .test();
    }

    @Test
    public void paperPaperTest() {
        BaseGameCalculatorTest
                .playersSymbol(PAPER)
                .appSymbol(PAPER)
                .expectedResult(DRAW)
                .test();
    }

    @Test
    public void paperScissorsTest() {
        BaseGameCalculatorTest
                .playersSymbol(PAPER)
                .appSymbol(SCISSORS)
                .expectedResult(LOSE)
                .test();
    }

    @Test
    public void scissorsRockTest() {
        BaseGameCalculatorTest
                .playersSymbol(SCISSORS)
                .appSymbol(ROCK)
                .expectedResult(LOSE)
                .test();
    }

    @Test
    public void scissorsPaperTest() {
        BaseGameCalculatorTest
                .playersSymbol(SCISSORS)
                .appSymbol(PAPER)
                .expectedResult(WIN)
                .test();
    }

    @Test
    public void scissorsScissorsTest() {
        BaseGameCalculatorTest
                .playersSymbol(SCISSORS)
                .appSymbol(SCISSORS)
                .expectedResult(DRAW)
                .test();
    }

    private static class BaseGameCalculatorTest {

        private static final GameResultCalculator calculator = new GameResultCalculator();
        private Symbol playersSymbol;
        private Symbol appSymbol;
        private Result expectedResult;

        private static BaseGameCalculatorTest playersSymbol(Symbol playersSymbol) {
            BaseGameCalculatorTest baseGameCalculatorTest = new BaseGameCalculatorTest();
            baseGameCalculatorTest.playersSymbol = playersSymbol;
            return baseGameCalculatorTest;
        }

        private BaseGameCalculatorTest appSymbol(Symbol appSymbol) {
            this.appSymbol = appSymbol;
            return this;
        }

        private BaseGameCalculatorTest expectedResult(Result expectedResult) {
            this.expectedResult = expectedResult;
            return this;
        }

        private void test() {
            LocalDateTime beforeCalculation = LocalDateTime.now();
            GameResult result = calculator.calculate(playersSymbol, appSymbol);
            assertEquals(playersSymbol, result.getPlayerSymbol());
            assertEquals(appSymbol, result.getAppsSymbol());
            assertEquals(expectedResult, result.getResult());
            assertFalse(result.getGameDateTime().isBefore(beforeCalculation));
            assertFalse(result.getGameDateTime().isAfter(LocalDateTime.now()));
        }
    }
}
