package com.connect4.model;

import com.connect4.model.builder.GameInstanceBuilder;
import com.connect4.model.builder.PlayerBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmorenoh on 12/11/16.
 */
public class    GameInstance {
    private String sessionIdentifier;
    private Map<TokenType, Player> players = new HashMap<>();
    private BoardGame boardGame = new BoardGame();
    private Player lastTurnPlayer;
    private Player winnerPlayer;

    public static GameInstance createGameInstance(String sessionIdentifier) {
        Player player1 = PlayerBuilder.aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build();
        Player player2 = PlayerBuilder.aPlayerBuilder().withTokenType(TokenType.TOKEN_TWO).build();
        return GameInstanceBuilder.aGameInstanceBuilder().withSessionIdentifier(sessionIdentifier)
                .withPlayer(player1).withPlayer(player2).build();

    }

    public void setWinnerPlayer(Player winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setSessionIdentifier(String sessionIdentifier) {
        this.sessionIdentifier = sessionIdentifier;
    }

    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    public Map<TokenType, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<TokenType, Player> players) {
        this.players = players;
    }

    public BoardGame getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public void setLastTurnPlayer(Player lastTurnPlayer) {
        this.lastTurnPlayer = lastTurnPlayer;
    }

    public Player getLastTurnPlayer() {
        return lastTurnPlayer;
    }
}
