package com.kkadoshnikov.rockpaperscissors.unit.tests;

import com.kkadoshnikov.rockpaperscissors.strategies.Strategy;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.game.GameResultCalculator;
import com.kkadoshnikov.rockpaperscissors.services.StrategiesService;
import com.kkadoshnikov.rockpaperscissors.services.GameService;
import com.kkadoshnikov.rockpaperscissors.services.SubscriberPlayEventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.DRAW;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for GameService class.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    private GameService gameService;
    @Mock
    private StrategiesService strategiesService;
    @Mock
    private GameResultCalculator gameResultCalculator;
    @Mock
    private SubscriberPlayEventService subscriberPlayEventService;
    @Mock
    private Strategy strategy;


    @Before
    public void setUp() {
        gameService = new GameService(strategiesService, gameResultCalculator, subscriberPlayEventService);
    }

    @Test
    public void playTest() {
        GameResult expectedResult = new GameResult(ROCK, ROCK, DRAW, LocalDateTime.now());

        when(strategiesService.getStrategy(1)).thenReturn(strategy);
        when(strategy.choose(1)).thenReturn(ROCK);
        when(gameResultCalculator.calculate(ROCK, ROCK)).thenReturn(expectedResult);

        GameResult actualResult = gameService.play(1, ROCK);

        assertEquals(expectedResult, actualResult);
        verify(strategiesService, Mockito.times(1)).getStrategy(1);
        verify(strategy, Mockito.times(1)).choose(1);
        verify(gameResultCalculator, Mockito.times(1)).calculate(ROCK, ROCK);
        verify(subscriberPlayEventService, Mockito.times(1)).afterGame(1, expectedResult);
    }
}
