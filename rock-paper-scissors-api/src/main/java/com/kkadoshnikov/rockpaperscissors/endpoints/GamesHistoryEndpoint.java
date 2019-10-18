package com.kkadoshnikov.rockpaperscissors.endpoints;

import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.game.GameStatistic;
import com.kkadoshnikov.rockpaperscissors.services.GameHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Games history endpoint.
 */
@Api(value = "Game", description = "Games history endpoint")
@RestController
@RequestMapping("/games-history")
@RequiredArgsConstructor
public class GamesHistoryEndpoint {

    private final GameHistoryService gameHistoryService;

    /**
     * Get history by player's Id.
     */
    @ApiOperation(value = "Get history by player Id.", produces = "application/json")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GameResult> getHistoryByUser(@QueryParam("playerId") Integer playerId) {
        return gameHistoryService.getHistoryByPlayerId(playerId);
    }

    /**
     * Get statistic by player's Id.
     */
    @ApiOperation(value = "Get statistic by player Id.", produces = "application/json")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/statistic")
    @ResponseBody
    public GameStatistic getStatistic(@QueryParam("playerId") Integer playerId) {
        return gameHistoryService.getStatistic(playerId);
    }
}
