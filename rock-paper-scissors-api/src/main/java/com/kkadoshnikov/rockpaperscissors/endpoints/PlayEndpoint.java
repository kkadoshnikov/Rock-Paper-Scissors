package com.kkadoshnikov.rockpaperscissors.endpoints;

import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import com.kkadoshnikov.rockpaperscissors.services.GameService;
import com.kkadoshnikov.rockpaperscissors.enums.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Game endpoint.
 */
@Api(value = "Play", description = "Play endpoint")
@RestController
@RequestMapping("/play")
@RequiredArgsConstructor
public class PlayEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayEndpoint.class);

    private final GameService gameService;

    /**
     * Http GET method.
     */
    @ApiOperation(value = "Play one game with Player.", produces = "application/json")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GameResult play(
            @RequestParam("playerId") Integer playerId,
            @RequestParam("playerItem") Item playerItem
    ) {
        return gameService.play(playerId, playerItem);
    }
}
