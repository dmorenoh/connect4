package com.connect4.utils;

import com.connect4.model.Player;
import com.connect4.model.TokenType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by dmorenoh on 13/11/16.
 */
public class Connect4UtilsTest {

    private static final String PLAYER_NAME_1 = "player1";
    private static final String PLAYER_NAME_2 = "player2";

    @Test
    public void getDuplicatedPlayersToken_emptyList_returnEmpty() {
        List<Player> result = Connect4Utils.getDuplicatedPlayersToken(new ArrayList<>());
        assertThat(result, is(empty()));
    }

    @Test
    public void getDuplicatedPlayersToken_nonDuplicatedToken_returnEmpty() {
        List<Player> players = Arrays.asList(aPlayer(PLAYER_NAME_1, TokenType.TOKEN_ONE),
                aPlayer(PLAYER_NAME_2, TokenType.TOKEN_TWO));
        List<Player> result = Connect4Utils.getDuplicatedPlayersToken(players);
        assertThat(result, is(empty()));
    }

    @Test
    public void getDuplicatedPlayersToken_duplicatedToken_returnDuplicatedItems() {
        Player duplicated1 = aPlayer(PLAYER_NAME_1, TokenType.TOKEN_ONE);
        Player duplicated2 = aPlayer(PLAYER_NAME_2, TokenType.TOKEN_TWO);
        List<Player> players =
                Arrays.asList(duplicated1,
                        duplicated2,
                        duplicated1,
                        duplicated2);

        List<Player> result = Connect4Utils.getDuplicatedPlayersToken(players);
        assertThat(result, is(not(empty())));
        assertThat(result.size(), equalTo(2));
        assertThat(result.contains(duplicated1), is(true));
        assertThat(result.contains(duplicated2), is(true));
    }

    private Player aPlayer(String name, TokenType tokenType) {
        Player player = new Player();
        player.setTokenType(tokenType);
        return player;
    }

}