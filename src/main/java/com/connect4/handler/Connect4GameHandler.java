package com.connect4.handler;

import com.connect4.checker.GameWinnerChecker;
import com.connect4.commands.MovementCommand;
import com.connect4.gameValidators.PlayerValidator;
import com.connect4.model.BoardPosition;
import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;
import com.connect4.model.Player;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class Connect4GameHandler {

    @Inject
    private PlayerValidator playerValidator;

    @Inject
    private MovementCommand movementCommand;

    @Inject
    private GameWinnerChecker gameWinnerChecker;

    public GameInstance play(GameInstance gameInstance, GameMovement gameMovement) {
        Player currentPlayer = gameMovement.getPlayer();
        playerValidator.validatePlayer(gameInstance, currentPlayer);
        BoardPosition positionPlayed = movementCommand.moveToken(gameInstance,gameMovement);
        gameInstance.setLastTurnPlayer(currentPlayer);
        checkIfCurrentPlayerWon(gameInstance, currentPlayer, positionPlayed);
        return gameInstance;
    }

    private void checkIfCurrentPlayerWon(GameInstance gameInstance, Player currentPlayer, BoardPosition position) {
        if (gameWinnerChecker.checkPlayerWinner(currentPlayer, position, gameInstance.getBoardGame())) {
            gameInstance.setWinnerPlayer(currentPlayer);
        }
    }


}
