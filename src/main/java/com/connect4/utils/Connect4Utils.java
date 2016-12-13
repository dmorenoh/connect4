package com.connect4.utils;

import com.connect4.model.Player;
import com.connect4.model.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by dmorenoh on 13/11/16.
 */
public class Connect4Utils {
    private Connect4Utils() {

    }

    public static List<Player> getDuplicatedPlayersToken(List<Player> players) {
        Map<TokenType, List<Player>> playersByTokenType = players.stream().collect(
                Collectors.groupingBy(Player::getTokenType));
        return getDuplicatedByKey(playersByTokenType);
    }

    private static List<Player> getDuplicatedByKey(Map<?, List<Player>> playersByKey) {
        List<Player> duplicatedPlayers = new ArrayList<>();
        playersByKey.values().stream()
                .filter(playerWithSameKey -> playerWithSameKey.size() > 1)
                .forEach(playerWithSameKey -> duplicatedPlayers.add(playerWithSameKey.get(0)));
        return duplicatedPlayers;
    }


}
