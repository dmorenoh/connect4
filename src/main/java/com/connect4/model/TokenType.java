package com.connect4.model;

/**
 * Created by dmorenoh on 12/11/16.
 */
public enum TokenType {
    TOKEN_ONE("1", "yellow"),
    TOKEN_TWO("0", "red");

    private final String symbol;
    private final String color;

    TokenType(String symbol, String color) {
        this.symbol = symbol;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
