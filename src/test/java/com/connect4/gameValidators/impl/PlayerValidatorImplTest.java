package com.connect4.gameValidators.impl;

import com.connect4.exceptions.GameException;
import com.connect4.model.GameInstance;
import com.connect4.model.Player;
import com.connect4.model.TokenType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.connect4.model.builder.GameInstanceBuilder.aGameInstanceBuilder;
import static com.connect4.model.builder.PlayerBuilder.aPlayerBuilder;


/**
 * Created by dmorenoh on 19/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerValidatorImplTest {
    @InjectMocks
    private PlayerValidatorImpl testSubject;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validatePlayer_playerNotFound_throwsException() {
        Player existingPlayer = aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build();
        GameInstance gameInstance = aGameInstanceBuilder().withPlayer(existingPlayer).build();
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Player not found");
        testSubject.validatePlayer(gameInstance, aPlayerBuilder().withTokenType(TokenType.TOKEN_TWO).build());
    }

    @Test
    public void validatePlayer_notPlayerTurn_throwsExceptio() {
        Player existingPlayer = aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build();
        GameInstance gameInstance = aGameInstanceBuilder()
                .withPlayer(existingPlayer)
                .withLastTurnPlayer(existingPlayer).build();
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Not your turn");
        testSubject.validatePlayer(gameInstance, existingPlayer);
    }

}