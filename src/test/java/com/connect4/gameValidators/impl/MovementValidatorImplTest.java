package com.connect4.gameValidators.impl;

import com.connect4.exceptions.GameException;
import com.connect4.model.BoardGame;
import com.connect4.model.GameMovement;
import com.connect4.model.Player;
import com.connect4.model.TokenType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by dmorenoh on 19/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovementValidatorImplTest {
    private static final int INVALID_COLUMN = 7;
    private static final int VALID_COLUMN = 3;
    @InjectMocks
    private MovementValidatorImpl testSubject;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validateMovement_NonValidToken_throwException() {
        GameMovement invalidGameMovement = aGameMovement(null);
        BoardGame boardGame = new BoardGame();
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Invalid Toke");
        testSubject.validateMovement(boardGame.getBoard(), invalidGameMovement);
    }

    @Test
    public void validateMovement_NonValidColumn_throwException() {
        GameMovement invalidGameMovement = aGameMovement(TokenType.TOKEN_ONE);
        BoardGame boardGame = new BoardGame();
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Invalid Column");
        testSubject.validateMovement(boardGame.getBoard(), invalidGameMovement);
    }

    private GameMovement aGameMovement(TokenType tokenType) {
        GameMovement gameMovement = new GameMovement();
        Player player = new Player();
        player.setTokenType(TokenType.TOKEN_ONE);

        gameMovement.setPlayer(player);
        gameMovement.setColumn(MovementValidatorImplTest.INVALID_COLUMN);
        return gameMovement;
    }
}