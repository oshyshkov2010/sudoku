package com.oshyshkov.games;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedSolverTest {
    @Test
    void solveSimpleBoard() {
        Board board = Board.fromString("003020600900305001001806400008102900700000008006708200002609500800203009005010300");
        String expectedResult =        "483921657967345821251876493548132976729564138136798245372689514814253769695417382";

        RandomizedSolver solver = new RandomizedSolver();
        assertTrue(solver.solve(board));
        assertEquals(expectedResult, board.toString());
    }

    @Test
    void solveHardBoard() {
        Board board = Board.fromString("850002400720000009004000000000107002305000900040000000000080070017000000000036040");
        String expectedResult =        "859612437723854169164379528986147352375268914241593786432981675617425893598736241";

        RandomizedSolver solver = new RandomizedSolver();
        assertTrue(solver.solve(board));
        assertEquals(expectedResult, board.toString());
    }

    @Test
    void solveEmptyBoard() {
        Board board = Board.emptyBoard();

        RandomizedSolver solver = new RandomizedSolver();
        assertTrue(solver.solve(board));
        System.out.println(board);
    }
}