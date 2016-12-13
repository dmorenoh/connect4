package com.connect4.model.builder;

import com.connect4.model.Player;
import com.connect4.model.TokenType;

/**
 * Created by dmorenoh on 19/11/16.
 */
public class PlayerBuilder {
    private final Player target = new Player();
    private TokenType tokenType;

    public static PlayerBuilder aPlayerBuilder() {
        return new PlayerBuilder();
    }

    public Player build(){
        target.setTokenType(this.tokenType);
        return target;
    }

    public PlayerBuilder withTokenType(TokenType tokenType){
        this.tokenType = tokenType;
        return this;
    }
}
