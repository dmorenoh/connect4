package com.connect4.gameValidators.impl;

import com.connect4.exceptions.GameException;
import com.connect4.gameValidators.MovementValidator;
import com.connect4.model.BoardGame;
import com.connect4.model.GameMovement;
import com.connect4.model.TokenType;
import org.springframework.stereotype.Component;

/**
 * Created by dmorenoh on 19/11/16.
 */
@Component
public class MovementValidatorImpl implements MovementValidator {
    @Override
    public void validateMovement(final TokenType[][] board, final GameMovement gameMovement) {
        checkValidToken(gameMovement.getPlayer().getTokenType());
        checkValidColumn(gameMovement.getColumn());

    }


    private void checkValidColumn(final Integer column) {
        if (column < 0 || column >= BoardGame.BOARD_MAX_COLUMNS) {
            throw new GameException("Invalid Column");
        }
    }

    private void checkValidToken(final TokenType tokenType) {
        if (tokenType == null) {
            throw new GameException("Invalid Token");
        }
    }
}
