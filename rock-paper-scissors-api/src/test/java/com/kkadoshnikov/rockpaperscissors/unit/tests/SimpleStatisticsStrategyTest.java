package com.kkadoshnikov.rockpaperscissors.unit.tests;

import com.kkadoshnikov.rockpaperscissors.strategies.probability.SimpleStatisticsStrategy;
import com.kkadoshnikov.rockpaperscissors.strategies.probability.StatisticsCounters;
import com.kkadoshnikov.rockpaperscissors.enums.Symbol;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.PAPER;
import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Symbol.SCISSORS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for SimpleStatisticsStrategy class.
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleStatisticsStrategyTest {

    private SimpleStatisticsStrategy simpleStatisticsStrategy;
    @Mock
    private StatisticsCounters statisticsCounters;

    @Before
    public void setUp() {
        simpleStatisticsStrategy = new SimpleStatisticsStrategy(statisticsCounters);
    }

    @Test
    public void chooseVsRockTest() {
        when(statisticsCounters.getMostLikelyplayerSymbol(1)).thenReturn(ROCK);

        Symbol actualResult = simpleStatisticsStrategy.choose(1);

        assertEquals(PAPER, actualResult);
        verify(statisticsCounters, Mockito.times(1)).getMostLikelyplayerSymbol(1);
    }

    @Test
    public void chooseVsPaperTest() {
        when(statisticsCounters.getMostLikelyplayerSymbol(1)).thenReturn(PAPER);

        Symbol actualResult = simpleStatisticsStrategy.choose(1);

        assertEquals(SCISSORS, actualResult);
        verify(statisticsCounters, Mockito.times(1)).getMostLikelyplayerSymbol(1);
    }

    @Test
    public void chooseVsScissorsTest() {
        when(statisticsCounters.getMostLikelyplayerSymbol(1)).thenReturn(SCISSORS);

        Symbol actualResult = simpleStatisticsStrategy.choose(1);

        assertEquals(ROCK, actualResult);
        verify(statisticsCounters, Mockito.times(1)).getMostLikelyplayerSymbol(1);
    }
}
