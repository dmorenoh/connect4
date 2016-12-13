package com.connect4.checker;

import com.connect4.model.BoardGame;
import com.connect4.model.BoardPosition;
import com.connect4.model.Player;

/**
 * Created by dmorenoh on 20/11/16.
 */
public interface GameWinnerChecker {
     boolean checkPlayerWinner(Player player, BoardPosition boardPosition, BoardGame boardGame);
}
