package com.connect4.gameValidators;

import com.connect4.model.GameMovement;
import com.connect4.model.TokenType;

/**
 * Created by dmorenoh on 19/11/16.
 */
public interface MovementValidator {
    void validateMovement(TokenType[][] board, GameMovement gameMovement);
}
