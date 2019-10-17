package com.kkadoshnikov.rockpaperscissors.endpoints;

import com.kkadoshnikov.rockpaperscissors.GameResult;
import com.kkadoshnikov.rockpaperscissors.services.GameService;
import com.kkadoshnikov.rockpaperscissors.enums.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Game endpoint.
 */
@Api(value = "Game", description = "Game endpoint")
@RestController
@RequestMapping("/play")
@RequiredArgsConstructor
public class GameEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameEndpoint.class);

    private final GameService gameService;

    /**
     * Http GET method.
     */
    @ApiOperation(value = "Indicates availability of Malt Api", produces = "application/json")
    @GetMapping
    @ResponseBody
    public GameResult play(
            @RequestParam("playerId") Integer playerId,
            @RequestParam("playersItem") Item playersItem
    ) {
        return gameService.play(playerId, playersItem);
    }
}
