package com.connect4.commands.impl;

import com.connect4.exceptions.GameException;
import com.connect4.gameValidators.MovementValidator;
import com.connect4.model.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.connect4.model.builder.GameInstanceBuilder.aGameInstanceBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * Created by dmorenoh on 20/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovementCommandImplTest {
    private static final String MESSAGE_EXCEPTION = "message";
    private static final int VALID_COLUMN = 3;
    @InjectMocks
    private MovementCommandImpl testSubject;

    @Mock
    private MovementValidator movementValidator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void moveToken_invalidMovement_throwsException() {
        doThrow(new GameException(MESSAGE_EXCEPTION))
                .when(movementValidator).validateMovement(any(TokenType[][].class), any(GameMovement.class));
        expectedException.expect(GameException.class);
        expectedException.expectMessage(MESSAGE_EXCEPTION);
        testSubject.moveToken(new GameInstance(), new GameMovement());
    }

    @Test
    public void moveToken_fullColumn_throwException() {
        doNothing().when(movementValidator).validateMovement(any(TokenType[][].class), any(GameMovement.class));
        GameMovement gameMovement = aGameMovement();
        GameInstance gameInstanceWithFullColum =
                aGameInstanceBuilder().withBoardGame(aBoardGameWithFullColumn()).build();
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Column is full");
        testSubject.moveToken(gameInstanceWithFullColum, gameMovement);
    }

    @Test
    public void moveToken_boardWithFirstAndSecondButtonRowPositionTaken_updateBoardInThirdButtonRow() {
        doNothing().when(movementValidator).validateMovement(any(TokenType[][].class), any(GameMovement.class));
        GameMovement gameMovement = aGameMovement();
        GameInstance gameInstance =
                aGameInstanceBuilder().withBoardGame(aBoardGameWithFistAndSecondRowButtonPostionTaken()).build();

        BoardPosition returned = testSubject.moveToken(gameInstance, gameMovement);
        assertThat(gameInstance.getBoardGame().getBoardItem(3, VALID_COLUMN-1), equalTo(TokenType.TOKEN_ONE));
        assertThat(returned.getRow(), equalTo(3));
        assertThat(returned.getColumn(), equalTo(VALID_COLUMN-1));
    }

    @Test
    public void moveToken_boardEmpty_updateBoardInFirstButtonRow() {
        doNothing().when(movementValidator).validateMovement(any(TokenType[][].class), any(GameMovement.class));
        GameMovement gameMovement = aGameMovement();
        GameInstance gameInstanceEmpty = aGameInstanceBuilder().build();
        BoardPosition returned = testSubject.moveToken(gameInstanceEmpty, gameMovement);
        assertThat(gameInstanceEmpty.getBoardGame().getBoardItem(BoardGame.BOARD_MAX_ROWS - 1, VALID_COLUMN - 1), equalTo(TokenType.TOKEN_ONE));
        assertThat(returned.getRow(), equalTo(BoardGame.BOARD_MAX_ROWS - 1));
        assertThat(returned.getColumn(), equalTo(VALID_COLUMN - 1));
    }

    private BoardGame aBoardGameWithFullColumn() {
        BoardGame boardGame = new BoardGame();
        for (int i = 0; i < BoardGame.BOARD_MAX_ROWS; i++) {
            boardGame.getBoard()[i][MovementCommandImplTest.VALID_COLUMN - 1] = TokenType.TOKEN_TWO;
        }
        return boardGame;
    }

    private BoardGame aBoardGameWithFistAndSecondRowButtonPostionTaken() {
        BoardGame boardGame = new BoardGame();
        boardGame.getBoard()[BoardGame.BOARD_MAX_ROWS - 1][VALID_COLUMN - 1] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[BoardGame.BOARD_MAX_ROWS - 2][VALID_COLUMN - 1] = TokenType.TOKEN_TWO;
        return boardGame;
    }


    private GameMovement aGameMovement() {
        GameMovement gameMovement = new GameMovement();
        Player player = new Player();
        player.setTokenType(TokenType.TOKEN_ONE);
        gameMovement.setColumn(VALID_COLUMN);
        gameMovement.setPlayer(player);
        return gameMovement;
    }
}