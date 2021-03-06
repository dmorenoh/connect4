package com.connect4.model;


public class BoardPosition {
    private final int row;
    private final int column;

    public BoardPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
