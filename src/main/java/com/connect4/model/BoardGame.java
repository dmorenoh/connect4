package com.connect4.model;

public class BoardGame {
    public final static Integer BOARD_MAX_ROWS = 6;
    public final static Integer BOARD_MAX_COLUMNS = 7;
    private final TokenType[][] board = new TokenType[BOARD_MAX_ROWS][BOARD_MAX_COLUMNS];

    public TokenType[][] getBoard() {
        return board;
    }

    public TokenType getBoardItem(Integer row, Integer column) {
        if (row < 0 || row >= BOARD_MAX_ROWS ||
                column < 0 || column >= BOARD_MAX_COLUMNS) {
            return null;
        }
        return board[row][column];
    }

}
