package com.oshyshkov.games;

import java.util.Random;

/*
    Generates a random Sudoku board with given number of empty cells using the following steps:
    - creates an empty board
    - fills diagonal boxes since they are independent of other matrices
    - runs a random solver that produces the solved board
    - removes given number of cells from solution.
*/
public class RandomGenerator implements Generator {
    private Random random = new Random();

    /**
     * Generates a partially solved Sudoku board.
     * @param emptyCellCount - number of empty cells int the board.
     */
    @Override
    public Board generate(int emptyCellCount) {
        assert (emptyCellCount > 0);
        Board board = Board.emptyBoard();

        // Fill diagonal boxes to improve performance.
        fillDiagonalBoxes(board);

        RandomizedSolver solver = new RandomizedSolver();
        solver.solve(board);
        assert(board.isSolved());

        setRandomEmptyCells(board, emptyCellCount);

        return board;
    }

    private void setRandomEmptyCells(Board board, int emptyCellCount) {
        while (emptyCellCount != 0) {
            int row = random.nextInt(Board.BOARD_SIZE);
            int column = random.nextInt(Board.BOARD_SIZE);
            if (!board.isEmpty(row, column)) {
                board.setEmpty(row, column);
                emptyCellCount--;
            }
        }
    }

    private void fillDiagonalBoxes(Board board) {
        // Not implemented.
    }
}
