package com.connect4.model.builder;

import com.connect4.model.BoardGame;
import com.connect4.model.GameInstance;
import com.connect4.model.Player;
import com.connect4.model.TokenType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmorenoh on 15/11/16.
 */
public class GameInstanceBuilder {
    private final GameInstance target = new GameInstance();

    private String sessionIdentifier;
    private final Map<TokenType, Player> players = new HashMap<>();
    private BoardGame boardGame = new BoardGame();
    private Player lastTurnPlayer;

    public static GameInstanceBuilder aGameInstanceBuilder() {
        return new GameInstanceBuilder();
    }

    public GameInstance build() {
        target.setSessionIdentifier(this.sessionIdentifier);
        target.setPlayers(this.players);
        target.setLastTurnPlayer(this.lastTurnPlayer);
        target.setBoardGame(this.boardGame);
        return target;
    }

    public GameInstanceBuilder withSessionIdentifier(String sessionIdentifier){
        this.sessionIdentifier = sessionIdentifier;
        return this;
    }

    public GameInstanceBuilder withPlayer(Player player){
        this.players.put(player.getTokenType(), player);
        return this;
    }

    public GameInstanceBuilder withLastTurnPlayer(Player lastTurnPlayer){
        this.lastTurnPlayer = lastTurnPlayer;
        return this;
    }

    public GameInstanceBuilder withBoardGame(BoardGame boardGame){
        this.boardGame = boardGame;
        return this;
    }
}
