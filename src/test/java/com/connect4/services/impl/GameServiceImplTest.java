package com.connect4.services.impl;

import com.connect4.exceptions.GameException;
import com.connect4.handler.Connect4GameHandler;
import com.connect4.helpers.SessionIdentifierGenerator;
import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;
import com.connect4.model.TokenType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by dmorenoh on 12/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    private static final String NEW_SESSION_KEY = "newSession";
    private static final String GAME_SESSION_IDENTIFIER = "gameSessionIdentifier";
    private static final String NOT_EXISTING_SESSION = "notExistingSession";
    @InjectMocks
    private GameServiceImpl testSubject;

    @Mock
    private SessionIdentifierGenerator sessionIdentifierGenerator;

    @Mock
    private Connect4GameHandler connect4GameHandler;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createNewGameSession_initValuesProvided_returnNewGameInstance() {
        when(sessionIdentifierGenerator.nextSessionId()).thenReturn(NEW_SESSION_KEY);
        GameInstance gameInstance =  testSubject.createNewGameSession();
        assertThat(gameInstance, is(not(nullValue())));
        assertThat(gameInstance.getSessionIdentifier(), equalTo(NEW_SESSION_KEY));
        assertPlayersList(gameInstance);
        assertThat(testSubject.getGameInstancesMap().get(NEW_SESSION_KEY), is(not(nullValue())));
        assertThat(testSubject.getGameInstancesMap().get(NEW_SESSION_KEY), equalTo(gameInstance));
    }

    @Test
    public void makeBoardMovement_gameSessionIdentifierNoFound_throwException() {

        ConcurrentHashMap<String, GameInstance> gameInstancesMap = aGameInstanceMap(new GameInstance());
        testSubject.setGameInstancesMap(gameInstancesMap);

        expectedException.expect(GameException.class);
        expectedException.expectMessage("Game session not found");
        testSubject.makeBoardMovement(NOT_EXISTING_SESSION, new GameMovement());
    }

    @Test
    public void makeBoardMovement_gameSessionFound_play(){
        GameInstance existingGameSession = new GameInstance();
        GameInstance updatedGameSession = new GameInstance();
        GameMovement gameMovement = new GameMovement();

        ConcurrentHashMap<String, GameInstance> gameInstancesMap = aGameInstanceMap(existingGameSession);
        testSubject.setGameInstancesMap(gameInstancesMap);

        when(connect4GameHandler.play(existingGameSession,gameMovement)).thenReturn(updatedGameSession);
        GameInstance result = testSubject.makeBoardMovement(GAME_SESSION_IDENTIFIER, gameMovement);
        verify(connect4GameHandler).play(existingGameSession, gameMovement);
        assertThat(result, equalTo(updatedGameSession));
    }

    private ConcurrentHashMap<String, GameInstance> aGameInstanceMap(GameInstance existingGameSession) {
        ConcurrentHashMap<String, GameInstance> gameInstancesMap= new ConcurrentHashMap<>();
        gameInstancesMap.put(GAME_SESSION_IDENTIFIER, existingGameSession);
        return gameInstancesMap;
    }

    private void assertPlayersList(GameInstance gameInstance) {
        assertThat(gameInstance.getPlayers().size(), equalTo(2));
        assertThat(gameInstance.getPlayers().containsKey(TokenType.TOKEN_ONE), is(true));
        assertThat(gameInstance.getPlayers().containsKey(TokenType.TOKEN_TWO), is(true));
    }

}