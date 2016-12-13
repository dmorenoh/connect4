package com.connect4.model;


/**
 * Created by dmorenoh on 20/11/16.
 */
public class GameMovement {

    private Player player;

    private Integer column;

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getColumn() {
        return column;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
