package com.oshyshkov.games;

import java.util.HashSet;
import java.util.Set;

public class SolverUtils {
    /**
     * Creates a string presentation of cell coordinate.
     * @param row - Row of a board.
     * @param col - Column of a board.
     * @return - String that represents the row and column in format rowcol. For example
     *           if row = 1 and col = 9, result is string "19".
     */
    public static String cellToString(int row, int col) {
        assert(row < 10 && row >= 0);
        assert(col < 10 && col >= 0);
        return Integer.toString(row) + Integer.toString(col);
    }

    /**
     * Returns the row of the cell.
     */
    public static int row(String cell) {
        return Integer.valueOf(cell.substring(0, 1));
    }

    /**
     * Returns the column of the cell.
     */
    public static int column(String cell) {
        return Integer.valueOf(cell.substring(1));
    }

    /**
     * Helper function that returns all empty cells on the boad.
     * @param board - Sudoku board.
     * @returns - List of all empty cells.
     */
    public static Set<String> getEmptyCells(Board board) {
        Set<String> cellsToVisit = new HashSet<>();
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                if (board.isEmpty(row, col)) {
                    cellsToVisit.add(SolverUtils.cellToString(row, col));
                }
            }
        }

        return cellsToVisit;
    }
}
