package com.connect4.checker.impl;

import com.connect4.model.BoardGame;
import com.connect4.model.BoardPosition;
import com.connect4.model.TokenType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.connect4.model.builder.PlayerBuilder.aPlayerBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by dmorenoh on 4/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameWinnerCheckerImplTest {
    @InjectMocks
    private GameWinnerCheckerImpl testSubject;

    @Test
    public void checkPlayerWinner_playerHasNowWin_returnFalse() {
        BoardGame boardGameWithoutWinner = aBoardGameWithoutWinner();
        boolean returned = testSubject.checkPlayerWinner(
                aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build(), new BoardPosition(3, 5), boardGameWithoutWinner);
        assertThat(returned, is(false));
    }


    @Test
    public void checkPlayerWinner_winnerIsAnotherPlayer_returnFalse() {
        BoardGame boardGameWithWinnerAsAnotherPlayer = aBoardGameWithWinnerAsAnotherPlayer();
        boolean returned = testSubject.checkPlayerWinner(
                aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build(), new BoardPosition(3, 5), boardGameWithWinnerAsAnotherPlayer);
        assertThat(returned, is(false));
    }

    @Test
    public void checkPlayerWinner_winnerIsCurrentPlayer_returnTrue() {
        BoardGame boardGameWithWinnerAsCurrentPlayer = aBoardGameWithWinnerAsCurrentPlayer();
        boolean returned = testSubject.checkPlayerWinner(
                aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build(), new BoardPosition(3, 5), boardGameWithWinnerAsCurrentPlayer);
        assertThat(returned, is(true));
    }

    @Test
    public void checkPlayerWinner_verticalLineWinner_returnTrue() {
        BoardGame boardGameVerticalLineWinner = aBoardGameWithVerticalLineWinner();
        boolean returned = testSubject.checkPlayerWinner(
                aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build(), new BoardPosition(2, 2), boardGameVerticalLineWinner);
        assertThat(returned, is(true));

    }

    @Test
    public void checkPlayerWinner_horizontalLineWinner_returnTrue() {
        BoardGame boardGameHorizontalLineWinner = aBoardGameWithHorizontalLineWinner();
        boolean returned = testSubject.checkPlayerWinner(
                aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build(), new BoardPosition(3, 3), boardGameHorizontalLineWinner);
        assertThat(returned, is(true));
    }

    @Test
    public void checkPlayerWinner_topLeftDiagonalLineWinner_returnTrue() {
        BoardGame boardGameTopLeftDiagonalLineWinner = aBoardGameWithTopLeftDiagonalLineWinner();
        boolean returned = testSubject.checkPlayerWinner(
                aPlayerBuilder().withTokenType(TokenType.TOKEN_ONE).build(), new BoardPosition(4, 4), boardGameTopLeftDiagonalLineWinner);
        assertThat(returned, is(true));
    }

    private BoardGame aBoardGameWithTopLeftDiagonalLineWinner() {
        BoardGame boardGame = new BoardGame();
        boardGame.getBoard()[2][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][4] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][5] = TokenType.TOKEN_ONE;

        boardGame.getBoard()[3][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][2] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][2] = TokenType.TOKEN_TWO;

        boardGame.getBoard()[3][4] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][5] = TokenType.TOKEN_TWO;

        return boardGame;
    }

    private BoardGame aBoardGameWithHorizontalLineWinner() {
        BoardGame boardGame = new BoardGame();
        boardGame.getBoard()[3][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][4] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][5] = TokenType.TOKEN_ONE;

        boardGame.getBoard()[4][1] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][1] = TokenType.TOKEN_TWO;

        boardGame.getBoard()[4][2] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][2] = TokenType.TOKEN_TWO;

        boardGame.getBoard()[4][3] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][3] = TokenType.TOKEN_TWO;
        return boardGame;
    }
    private BoardGame aBoardGameWithVerticalLineWinner() {
        BoardGame boardGame = new BoardGame();
        boardGame.getBoard()[2][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][1] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][1] = TokenType.TOKEN_TWO;
        return boardGame;
    }

    private BoardGame aBoardGameWithWinnerAsAnotherPlayer() {
        BoardGame boardGame = new BoardGame();
        boardGame.getBoard()[2][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[2][4] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[3][3] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][1] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][1] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][2] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][4] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][4] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][4] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[3][5] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][5] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][5] = TokenType.TOKEN_TWO;
        return boardGame;
    }

    private BoardGame aBoardGameWithoutWinner() {
        BoardGame boardGame = new BoardGame();
        boardGame.getBoard()[2][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][3] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][1] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][1] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][2] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][4] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][4] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][4] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[3][5] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][5] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][5] = TokenType.TOKEN_TWO;

        return boardGame;
    }

    private BoardGame aBoardGameWithWinnerAsCurrentPlayer() {
        BoardGame boardGame = new BoardGame();
        boardGame.getBoard()[2][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[2][4] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[3][3] = TokenType.TOKEN_TWO;

        boardGame.getBoard()[4][3] = TokenType.TOKEN_ONE;

        boardGame.getBoard()[5][3] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[4][4] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][5] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[2][6] = TokenType.TOKEN_ONE;

        boardGame.getBoard()[4][1] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[5][1] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][2] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][2] = TokenType.TOKEN_ONE;
        boardGame.getBoard()[3][4] = TokenType.TOKEN_TWO;

        boardGame.getBoard()[5][4] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[4][5] = TokenType.TOKEN_TWO;
        boardGame.getBoard()[5][5] = TokenType.TOKEN_TWO;
        return boardGame;
    }
}