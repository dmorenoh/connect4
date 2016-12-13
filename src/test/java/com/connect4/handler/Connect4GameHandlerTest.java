package com.connect4.handler;

import com.connect4.checker.GameWinnerChecker;
import com.connect4.commands.MovementCommand;
import com.connect4.exceptions.GameException;
import com.connect4.gameValidators.PlayerValidator;
import com.connect4.model.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Connect4GameHandlerTest {
    @InjectMocks
    private Connect4GameHandler testSubject;

    @Mock
    private PlayerValidator playerValidator;

    @Mock
    private MovementCommand movementCommand;

    @Mock
    private GameWinnerChecker gameWinnerChecker;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void play_validPlayerThrowsException_throwsException() {
        GameInstance gameInstance = new GameInstance();
        GameMovement gameMovement = new GameMovement();
        GameException gameException = new GameException("game exception");

        doThrow(gameException).when(playerValidator).validatePlayer(any(GameInstance.class), any(Player.class));
        expectedException.expect(GameException.class);
        expectedException.expectMessage(gameException.getMessage());
        testSubject.play(gameInstance, gameMovement);
        verify(playerValidator).validatePlayer(gameInstance, gameMovement.getPlayer());
        verify(movementCommand, never()).moveToken(any(GameInstance.class), any(GameMovement.class));
        verify(gameWinnerChecker, never()).checkPlayerWinner(any(Player.class), any(BoardPosition.class),  any(BoardGame.class));
    }

    @Test
    public void play_invalidMovement_throwsException() {
        GameInstance gameInstance = new GameInstance();
        GameMovement gameMovement = new GameMovement();
        GameException gameException = new GameException("game exception");
        doNothing().when(playerValidator).validatePlayer(any(GameInstance.class), any(Player.class));
        doThrow(gameException).when(movementCommand).moveToken(any(GameInstance.class), any(GameMovement.class));
        expectedException.expect(GameException.class);
        expectedException.expectMessage(gameException.getMessage());
        testSubject.play(gameInstance, gameMovement);
        verify(playerValidator).validatePlayer(gameInstance, gameMovement.getPlayer());
        verify(movementCommand).moveToken(gameInstance, gameMovement);
        verify(gameWinnerChecker, never()).checkPlayerWinner(any(Player.class), any(BoardPosition.class),any(BoardGame.class));
    }

    @Test
    public void play_validMovementPlayerDidNotWin_updateLastPlayerTurn() {
        GameInstance gameInstance = new GameInstance();
        GameMovement gameMovement = new GameMovement();
        BoardPosition positionPlayed = new BoardPosition(3, 3);
        doNothing().when(playerValidator).validatePlayer(any(GameInstance.class), any(Player.class));
        when(movementCommand.moveToken(any(GameInstance.class), any(GameMovement.class))).thenReturn(positionPlayed);
        when(gameWinnerChecker.checkPlayerWinner(any(Player.class), any(BoardPosition.class), any(BoardGame.class))).thenReturn(false);
        GameInstance returned = testSubject.play(gameInstance, gameMovement);
        verify(playerValidator).validatePlayer(gameInstance, gameMovement.getPlayer());
        verify(movementCommand).moveToken(gameInstance, gameMovement);
        verify(gameWinnerChecker).checkPlayerWinner(gameMovement.getPlayer(), positionPlayed, gameInstance.getBoardGame());
        assertThat(returned.getLastTurnPlayer(), equalTo(gameMovement.getPlayer()));
    }

    @Test
    public void play_validMovementPlayerDidWin_updateLastPlayerTurnAndGameInstanceAsWinnerFound() {
        GameInstance gameInstance = new GameInstance();
        GameMovement gameMovement = new GameMovement();
        doNothing().when(playerValidator).validatePlayer(any(GameInstance.class), any(Player.class));
        BoardPosition position = new BoardPosition(3, 3);
        when(movementCommand.moveToken(any(GameInstance.class), any(GameMovement.class))).thenReturn(position);
        when(gameWinnerChecker.checkPlayerWinner(any(Player.class), any(BoardPosition.class), any(BoardGame.class))).thenReturn(true);
        GameInstance returned = testSubject.play(gameInstance, gameMovement);
        verify(playerValidator).validatePlayer(gameInstance, gameMovement.getPlayer());
        verify(movementCommand).moveToken(gameInstance, gameMovement);
        verify(gameWinnerChecker).checkPlayerWinner(gameMovement.getPlayer(), position, gameInstance.getBoardGame());
        assertThat(returned.getLastTurnPlayer(), equalTo(gameMovement.getPlayer()));
        assertThat(returned.getWinnerPlayer(), equalTo(gameMovement.getPlayer()));
    }
}