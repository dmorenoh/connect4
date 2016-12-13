package com.connect4.gameValidators;

import com.connect4.model.GameInstance;
import com.connect4.model.Player;

/**
 * Created by dmorenoh on 19/11/16.
 */
public interface PlayerValidator {
    void validatePlayer(GameInstance gameInstance, Player player);
}
