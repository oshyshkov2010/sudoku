package com.oshyshkov.games;

import java.util.List;

public class Result {
    private char[][] board;
    private List<Cell> errors;
    private boolean updateError;

    public Result(char[][] board, List<Cell> errors, boolean updateError) {
        this.board = board;
        this.errors = errors;
        this.updateError = updateError;
    }

    public List<Cell> getErrors() {
        return errors;
    }
}
