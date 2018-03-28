package com.oshyshkov.games;

public class BacktrackingSolver implements Solver{
    private int iterations = 0;

    public boolean solve(Board board) {
        return solve(board, 0, 0);
    }

    public boolean solve(Board board, int row, int column) {
        iterations++;
        if (row == Board.BOARD_SIZE) {
            return board.isSolved();
        }

        int nextColumn = column == Board.LAST_COLUMN ? 0 : column + 1;
        int nextRow = column == Board.LAST_COLUMN ? row + 1 : row;

        if (!board.isEmpty(row, column)) {
            if (solve(board, nextRow, nextColumn)) {
                return true;
            }
        } else {
            for (char number : Board.VALID_NUMBERS) {
                if (board.isValidAssignment(row, column, number)) {
                    board.setNumber(row, column, number);
                    if (solve(board, nextRow, nextColumn)) {
                        return true;
                    }

                    board.setEmpty(row, column);
                }
            }
        }

        if (iterations % 100000 == 0) {
            System.out.println("Iterations: " + iterations + " com.oshyshkov.games.Board: " + board.toString());
        }

        return false;
    }
}
