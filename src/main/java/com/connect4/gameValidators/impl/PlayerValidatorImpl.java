package com.connect4.gameValidators.impl;

import com.connect4.exceptions.GameException;
import com.connect4.gameValidators.PlayerValidator;
import com.connect4.model.GameInstance;
import com.connect4.model.Player;
import org.springframework.stereotype.Component;

/**
 * Created by dmorenoh on 19/11/16.
 */
@Component
public class PlayerValidatorImpl implements PlayerValidator {

    @Override
    public void validatePlayer(final GameInstance gameInstance, final Player player) {
        checkExistingPlayer(gameInstance, player);
        Player currentPlayer = gameInstance.getPlayers().get(player.getTokenType());
        checkExistingWinner(gameInstance);
        checkValidPlayerTurn(gameInstance, currentPlayer);
    }

    private void checkExistingWinner(final GameInstance gameInstance) {
        if (gameInstance.getWinnerPlayer() != null) {
            throw new GameException("There is already a winner");
        }
    }

    private void checkExistingPlayer(GameInstance gameInstance, Player player) {
        if (!gameInstance.getPlayers().containsKey(player.getTokenType())) {
            throw new GameException("Player not found");
        }
    }

    private void checkValidPlayerTurn(GameInstance gameInstance, Player player) {
        if (player.equals(gameInstance.getLastTurnPlayer())) {
            throw new GameException("Not your turn");
        }
    }
}
