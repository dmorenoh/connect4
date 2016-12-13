package com.connect4.checker.impl;

import com.connect4.checker.GameWinnerChecker;
import com.connect4.model.BoardGame;
import com.connect4.model.BoardPosition;
import com.connect4.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dmorenoh on 20/11/16.
 */
@Component
public class GameWinnerCheckerImpl implements GameWinnerChecker {

    @Override
    public boolean checkPlayerWinner(Player player, BoardPosition boardPosition, BoardGame boardGame) {
        return checkWinner(player, boardPosition, boardGame);
    }

    private boolean checkWinner(Player player, BoardPosition boardPosition, BoardGame boardGame) {
        List<BoardPosition> verticalLine = getVerticalLine(boardPosition);
        List<BoardPosition> horizontalLine = getHorizontalLine(boardPosition);
        List<BoardPosition> topLeftDiagonal = getTopLeftDiagonalLine(boardPosition);
        List<BoardPosition> bottomLeftDiagonal = getBottomLeftDiagonalLine(boardPosition);

        return checkWinnerInLine(player, verticalLine, boardGame) ||
                checkWinnerInLine(player, horizontalLine, boardGame) ||
                checkWinnerInLine(player, topLeftDiagonal, boardGame) ||
                checkWinnerInLine(player, bottomLeftDiagonal, boardGame);

    }

    private boolean checkWinnerInLine(Player player, List<BoardPosition> line, BoardGame boardGame) {

        int countCoincidences = 0;

        for (BoardPosition point : line) {
            if (player.getTokenType().equals(boardGame.getBoardItem(point.getRow(), point.getColumn()))) {
                countCoincidences++;
                if (countCoincidences == 4) {
                    return true;
                }
            } else {
                countCoincidences = 0;
            }
        }
        return false;
    }


    private List<BoardPosition> getBottomLeftDiagonalLine(BoardPosition position) {
        BoardPosition bottomLeftPoint = new BoardPosition(position.getRow() + 3, position.getColumn() - 3);

        return IntStream
                .rangeClosed(0, 6)
                .mapToObj(i -> new BoardPosition(bottomLeftPoint.getRow() - i, bottomLeftPoint.getColumn() + i))
                .collect(Collectors.toList());
    }

    private List<BoardPosition> getTopLeftDiagonalLine(BoardPosition position) {
        BoardPosition topLeftPoint = new BoardPosition(position.getRow() - 3, position.getColumn() - 3);
        return IntStream
                .rangeClosed(0, 6)
                .mapToObj(i -> new BoardPosition(topLeftPoint.getRow() + i, topLeftPoint.getColumn() + i))
                .collect(Collectors.toList());
    }

    private List<BoardPosition> getHorizontalLine(BoardPosition position) {
        return IntStream
                .rangeClosed(position.getColumn() - 3, position.getColumn() + 3)
                .mapToObj(column -> new BoardPosition(position.getRow(), column))
                .collect(Collectors.toList());
    }

    private List<BoardPosition> getVerticalLine(BoardPosition currentPosition) {
        return IntStream
                .rangeClosed(currentPosition.getRow() - 3, currentPosition.getRow() + 3)
                .mapToObj(row -> new BoardPosition(row, currentPosition.getColumn()))
                .collect(Collectors.toList());
    }

}
