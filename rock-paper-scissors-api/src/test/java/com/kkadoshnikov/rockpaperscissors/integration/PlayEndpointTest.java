package com.kkadoshnikov.rockpaperscissors.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkadoshnikov.rockpaperscissors.enums.Item;
import com.kkadoshnikov.rockpaperscissors.enums.Result;
import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.kkadoshnikov.rockpaperscissors.enums.Item.ROCK;
import static com.kkadoshnikov.rockpaperscissors.enums.Result.LOSE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for /play endpoint.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PlayEndpointTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void playOneGameTest() throws Exception {
        GameResult gameResult = playOneGame(1, ROCK);

        assertEquals(ROCK, gameResult.getPlayerItem());
        assertNotNull(gameResult.getAppsItem());
        assertNotNull(gameResult.getResult());
        assertNotNull(gameResult.getGameDateTime());
    }

    @Test
    public void playTenGameTest() throws Exception {
        // Prepare statistics for SimpleStatisticsAlgorithm.
        for (int i = 0; i < 5; i++) {
            playOneGame(1, ROCK);
        }
        // SimpleStatisticsAlgorithm must predict that player chooses ROCK and choose PAPER.
        for (int i = 0; i < 30; i++) {
            Result actualResult = playOneGame(1, ROCK).getResult();
            assertEquals(LOSE, actualResult);
        }
    }

    private GameResult playOneGame(Integer playerId, Item playerItem) throws Exception {
        String result = mvc.perform(get("/play")
                .param("playerId", playerId.toString())
                .param("playerItem", playerItem.name()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(result, GameResult.class);
    }
}
