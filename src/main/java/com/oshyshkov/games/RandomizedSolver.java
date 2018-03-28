package com.oshyshkov.games;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class RandomizedSolver implements Solver {
    private int iterations = 0;
    private int depth = 0;
    private Random random = new Random();
    private Character[] values;

    public RandomizedSolver() {
        values = Arrays.copyOf(Board.NUMBERS, Board.NUMBERS.length);
    }

    private void shuffleValues() {
        for (int i = 0; i < values.length; i++) {
            int j = i + random.nextInt(values.length - i);
            swap(values, i, j);
        }
    }

    private static void swap(Character[] values, int i, int j) {
        Character temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

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

        if (cell == null) {
            return board.isSolved();
        }

        if (cellsToVisit.isEmpty()) {
            return board.isSolved();
        }

        cellsToVisit.remove(cell);

        int column = SolverUtils.column(cell);
        int row = SolverUtils.row(cell);

        String nextCell = findMinimum(board, cellsToVisit);
        if (nextCell == null && board.emptyCellsCount() > 1) {
            cellsToVisit.add(cell);
            return false;
        }

        depth++;
        shuffleValues();
        for (char number : values) {
            if (board.isValidAssignment(row, column, number)) {
                board.setNumber(row, column, number);
                //nextCell = findMinimum(board, cellsToVisit);
                if (solve(board, nextCell, cellsToVisit)) {
                    depth--;
                    return true;
                }

                board.setEmpty(row, column);
            }
        }

        if (iterations % 100000 == 0) {
            System.out.println("Iterations: " + iterations + " Depth: " + depth + " com.oshyshkov.games.Board: " + board.toString());
        }

        cellsToVisit.add(cell);
        depth--;
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
                if (min == 0) {
                    return null;
                }
            }
        }

        return minCell;
    }
}
