package com.connect4.commands;

import com.connect4.model.BoardPosition;
import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;

/**
 * Created by dmorenoh on 20/11/16.
 */
public interface MovementCommand {
    BoardPosition moveToken(GameInstance gameInstance, GameMovement movement);
}
