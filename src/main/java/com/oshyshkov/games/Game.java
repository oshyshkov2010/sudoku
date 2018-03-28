package com.oshyshkov.games;

import java.util.*;


public class Game {
    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public Result setNumber(int row, int col, char number) {
        if (board.isImmutable(row, col)) {
            return updateErrorResult();
        }
        if (!Board.VALID_NUMBERS.contains(number)) {
            return updateErrorResult();
        }
        board.setNumber(row, col, number);

        return result();
    }

    private Result updateErrorResult() {
        return new Result(board.toCharView(), getErrors(), false);
    }

    private Result result() {
        return new Result(board.toCharView(), getErrors(), false);
    }

    private List<Cell> getErrors() {
        List<Cell> errors = new ArrayList<>();
        Set<Cell> currentDuplicates = board.getNonEmptyDuplicates();

        for (Cell cell : currentDuplicates) {
            if (!cell.isImmutable()) {
                errors.add(cell);
            }
        }

        return errors;
    }
}
