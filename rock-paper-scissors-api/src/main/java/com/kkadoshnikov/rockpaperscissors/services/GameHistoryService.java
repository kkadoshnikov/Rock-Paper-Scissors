package com.kkadoshnikov.rockpaperscissors.services;

import com.kkadoshnikov.rockpaperscissors.game.GameResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Games history service.
 * This is initial version with in-memory storage.
 */
@Service
public class GameHistoryService implements PlayEventSubscriber {

    //ToDo: add storage for game history instead of map.
    private final Map<Integer, List<GameResult>> userGamesHistory = new ConcurrentHashMap<>();

    @Override
    public void afterGame(Integer playerId, GameResult gameResult) {
        List<GameResult> results = userGamesHistory.putIfAbsent(playerId, new CopyOnWriteArrayList<>());
        if (results == null) {
            results = userGamesHistory.get(playerId);
        }
        results.add(gameResult);
    }

    /**
     * Get Player's games count.
     * @param playerId - player's Id.
     * @return Player's games count.
     */
    public Integer getGameCount(Integer playerId) {
        if (!userGamesHistory.containsKey(playerId)) {
            return 0;
        }
        return userGamesHistory.get(playerId).size();
    }

    /**
     * Get games history by Player's Id.
     */
    public List<GameResult> getHistoryByPlayerId(Integer playerId) {
        return Collections.unmodifiableList(userGamesHistory.getOrDefault(playerId, Collections.emptyList()));
    }
}
