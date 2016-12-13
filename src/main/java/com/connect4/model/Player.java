package com.connect4.model;

/**
 * Created by dmorenoh on 12/11/16.
 */
public class Player {

    private TokenType tokenType;

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Player)) {
            return false;
        }

        Player that = (Player) object;
        return this.getTokenType().equals(that.getTokenType()   );

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + tokenType.hashCode();
        return result;
    }
}
