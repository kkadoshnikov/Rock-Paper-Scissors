package com.kkadoshnikov.rockpaperscissors.unit.tests;

import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.game.GameResultCalculator;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.kkadoshnikov.rockpaperscissors.enums.Item.PAPER;
import static com.kkadoshnikov.rockpaperscissors.enums.Item.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Item.SCISSORS;
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
                .playersItem(ROCK)
                .appItem(ROCK)
                .expectedResult(DRAW)
                .test();
    }

    @Test
    public void rockPaperTest() {
        BaseGameCalculatorTest
                .playersItem(ROCK)
                .appItem(PAPER)
                .expectedResult(LOSE)
                .test();
    }

    @Test
    public void rockScissorsTest() {
        BaseGameCalculatorTest
                .playersItem(ROCK)
                .appItem(SCISSORS)
                .expectedResult(WIN)
                .test();
    }

    @Test
    public void paperRockTest() {
        BaseGameCalculatorTest
                .playersItem(PAPER)
                .appItem(ROCK)
                .expectedResult(WIN)
                .test();
    }

    @Test
    public void paperPaperTest() {
        BaseGameCalculatorTest
                .playersItem(PAPER)
                .appItem(PAPER)
                .expectedResult(DRAW)
                .test();
    }

    @Test
    public void paperScissorsTest() {
        BaseGameCalculatorTest
                .playersItem(PAPER)
                .appItem(SCISSORS)
                .expectedResult(LOSE)
                .test();
    }

    @Test
    public void scissorsRockTest() {
        BaseGameCalculatorTest
                .playersItem(SCISSORS)
                .appItem(ROCK)
                .expectedResult(LOSE)
                .test();
    }

    @Test
    public void scissorsPaperTest() {
        BaseGameCalculatorTest
                .playersItem(SCISSORS)
                .appItem(PAPER)
                .expectedResult(WIN)
                .test();
    }

    @Test
    public void scissorsScissorsTest() {
        BaseGameCalculatorTest
                .playersItem(SCISSORS)
                .appItem(SCISSORS)
                .expectedResult(DRAW)
                .test();
    }

    private static class BaseGameCalculatorTest {

        private static final GameResultCalculator calculator = new GameResultCalculator();
        private Item playersItem;
        private Item appItem;
        private Result expectedResult;

        private static BaseGameCalculatorTest playersItem(Item playersItem) {
            BaseGameCalculatorTest baseGameCalculatorTest = new BaseGameCalculatorTest();
            baseGameCalculatorTest.playersItem = playersItem;
            return baseGameCalculatorTest;
        }

        private BaseGameCalculatorTest appItem(Item appItem) {
            this.appItem = appItem;
            return this;
        }

        private BaseGameCalculatorTest expectedResult(Result expectedResult) {
            this.expectedResult = expectedResult;
            return this;
        }

        private void test() {
            LocalDateTime beforeCalculation = LocalDateTime.now();
            GameResult result = calculator.calculate(playersItem, appItem);
            assertEquals(playersItem, result.getPlayerItem());
            assertEquals(appItem, result.getAppsItem());
            assertEquals(expectedResult, result.getResult());
            assertFalse(result.getGameDateTime().isBefore(beforeCalculation));
            assertFalse(result.getGameDateTime().isAfter(LocalDateTime.now()));
        }
    }
}
