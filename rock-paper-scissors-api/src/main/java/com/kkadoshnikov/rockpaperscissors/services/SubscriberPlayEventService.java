package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SyndicationEventService implementation.
 */
@Service
public class SubscriberPlayEventService {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriberPlayEventService.class);
    private final List<PlayEventSubscriber> subscribers;

    @Autowired
    public SubscriberPlayEventService(List<PlayEventSubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void afterGame(Integer playerId, GameResult gameResult) {
        subscribers.forEach(subscriber -> {
            try {
                subscriber.afterGame(playerId, gameResult);
            } catch (RuntimeException ex) {
                LOG.warn("After game event was failed for subscriber {}. Error: {}", subscriber.getName(), ex);
            }
        });
    }
}
