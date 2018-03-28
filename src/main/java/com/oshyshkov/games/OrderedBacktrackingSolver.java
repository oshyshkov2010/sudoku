package com.oshyshkov.games;

import java.util.Set;

public class OrderedBacktrackingSolver implements Solver {
    private int iterations = 0;

    public boolean solve(Board board) {
        Set<String> cellsToVisit =  SolverUtils.getEmptyCells(board);

        if (cellsToVisit.isEmpty()) {
            return true;
        }

        String start = findMinimum(board, cellsToVisit);
        return solve(board, start, cellsToVisit);
    }

    private boolean solve(Board board, String cell, Set<String> cellsToVisit) {
        iterations++;

        if (cellsToVisit.isEmpty()) {
            return board.isSolved();
        }

        cellsToVisit.remove(cell);

        int column = SolverUtils.column(cell);
        int row = SolverUtils.row(cell);

        String nextCell = findMinimum(board, cellsToVisit);

        for (char number : Board.VALID_NUMBERS) {
            if (board.isValidAssignment(row, column, number)) {
                board.setNumber(row, column, number);
                if (solve(board, nextCell, cellsToVisit)) {
                    return true;
                }

                board.setEmpty(row, column);
            }
        }

        if (iterations % 100000 == 0) {
            System.out.println("Iterations: " + iterations + " com.oshyshkov.games.Board: " + board.toString());
        }

        cellsToVisit.add(cell);

        return false;
    }

    private String findMinimum(Board board, Set<String> cellsToVisit) {
        int min = Integer.MAX_VALUE;
        String minCell = null;
        for (String cell : cellsToVisit) {
            int candidateValuesSize = board.getCandidateValues(SolverUtils.row(cell), SolverUtils.column(cell)).size();
            if (candidateValuesSize < min) {
                min = candidateValuesSize;
                minCell = cell;
            }
        }

        return minCell;
    }
}