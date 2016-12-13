package com.connect4.commands.impl;

import com.connect4.commands.MovementCommand;
import com.connect4.exceptions.GameException;
import com.connect4.gameValidators.MovementValidator;
import com.connect4.model.BoardGame;
import com.connect4.model.BoardPosition;
import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by dmorenoh on 20/11/16.
 */
@Component
public class MovementCommandImpl implements MovementCommand {

    @Inject
    private MovementValidator movementValidator;

    @Override
    public BoardPosition moveToken(GameInstance gameInstance, GameMovement movement) {
        movementValidator.validateMovement(gameInstance.getBoardGame().getBoard(), movement);
        return makeTokenMovement(gameInstance.getBoardGame(), movement);
    }

    private BoardPosition makeTokenMovement(BoardGame boardGame, GameMovement movement) {
        boolean movementDone = false;
        Integer currentRow = BoardGame.BOARD_MAX_ROWS - 1;
        while (!movementDone && currentRow >= 0) {
            if (boardGame.getBoard()[currentRow][movement.getColumn() - 1] == null) {
                boardGame.getBoard()[currentRow][movement.getColumn() - 1] = movement.getPlayer().getTokenType();
                movementDone = true;
            } else {
                currentRow--;
            }
        }

        if (!movementDone && currentRow < 0) {
            throw new GameException("Column is full");
        }

        return new BoardPosition(currentRow, movement.getColumn()-1);

    }
}
