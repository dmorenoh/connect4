package com.connect4.services.impl;

import com.connect4.exceptions.GameException;
import com.connect4.handler.Connect4GameHandler;
import com.connect4.helpers.SessionIdentifierGenerator;
import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;
import com.connect4.services.GameService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dmorenoh on 12/11/16.
 */
@Service
public class GameServiceImpl implements GameService {

    @Inject
    private SessionIdentifierGenerator sessionIdentifierGenerator;

    @Inject
    private Connect4GameHandler connect4GameHandler;

    private ConcurrentHashMap<String, GameInstance> gameInstanceMap = new ConcurrentHashMap<>();

    @Override
    public GameInstance createNewGameSession() {
        String gameSessionIdentifier = sessionIdentifierGenerator.nextSessionId();
        GameInstance newGameInstance = GameInstance.createGameInstance(gameSessionIdentifier);
        gameInstanceMap.put(gameSessionIdentifier, newGameInstance);
        return newGameInstance;
    }

    @Override
    public GameInstance makeBoardMovement(String gameSessionKey, GameMovement gameMovement) {
        if (!gameInstanceMap.containsKey(gameSessionKey)) {
            throw new GameException("Game session not found");
        }
        synchronized (gameInstanceMap.get(gameSessionKey)) {
            return connect4GameHandler.play(gameInstanceMap.get(gameSessionKey), gameMovement);
        }
    }

    public ConcurrentHashMap<String, GameInstance> getGameInstancesMap() {
        return gameInstanceMap;
    }

    public void setGameInstancesMap(ConcurrentHashMap<String, GameInstance> connect4HandlersMap) {
        this.gameInstanceMap = connect4HandlersMap;
    }
}
