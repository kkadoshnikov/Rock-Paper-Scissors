package com.kkadoshnikov.rockpaperscissors.endpoints.tech;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ping endpoint.
 */
@Api(value = "Ping", description = "Ping endpoint")
@RestController
@RequestMapping("/ping")
public class PingEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingEndpoint.class);

    /**
     * Http GET method.
     */
    @ApiOperation(value = "Indicates availability of Malt Api", produces = "text/plain")
    @GetMapping
    @ResponseBody
    public String get() {
        LOGGER.info("Call ping");
        return "pong";
    }
}
