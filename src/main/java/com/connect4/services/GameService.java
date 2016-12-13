package com.connect4.services;

import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;

/**
 * Created by dmorenoh on 12/11/16.
 */
public interface GameService {
    GameInstance createNewGameSession();

    GameInstance makeBoardMovement(String gameSessionKey, GameMovement gameMovement);
}
