package com.oshyshkov.games;

import java.util.List;

public class PreprocessedSolver implements Solver {

    private static class EmptyCell {
        // Each index int the array indicates if the number can be assigned
        // to the cell.
        private boolean[] availableNumbers;

        private int row;

        private int column;

        // How many number can be assigned to the cell.
        private int availableNumberCount;

        EmptyCell(int row, int column) {
            this.row = row;
            this.column = column;
            availableNumberCount = Board.NUMBERS.length;
            availableNumbers = new boolean[Board.NUMBERS.length];

            for (int i = 0; i < availableNumbers.length; i++) {
                availableNumbers[i] = true;
            }
        }

        void setNumber(int numberIndex) {
            availableNumbers[numberIndex] = false;
        }

        void unsetNumber(int numberIndex) {
            availableNumbers[numberIndex] = true;
        }

        int getAvailableNumberCount() {
            return availableNumberCount;
        }
    }

    private static class SlovingState {
        private List<EmptyCell> cellsToVisit;
        private int nextUnsolvedIndex;

        public SlovingState(List<EmptyCell> cellsToVisit) {
            this.cellsToVisit = cellsToVisit;
        }

        public void updateAvailability(Board board) {

        }
    }

    @Override
    public boolean solve(Board board) {
        List<EmptyCell> cellsToVisit = findEmptyCells(board);

        SlovingState state = new SlovingState(cellsToVisit);
        state.updateAvailability(board);
        return solve(board, state);
    }

    private boolean solve(Board board, SlovingState state) {
        return false;
    }

    private List<EmptyCell> findEmptyCells(Board board) {
        return null;
    }
}
