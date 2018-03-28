package com.oshyshkov.games;

import com.oshyshkov.games.Board;
import com.oshyshkov.games.OrderedBacktrackingSolver;
import com.oshyshkov.games.RandomGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorTest {
    @Test
    void generatedBoardCanBeSolved() {
        RandomGenerator generator = new RandomGenerator();
        Board board = generator.generate(40);

        OrderedBacktrackingSolver solver = new OrderedBacktrackingSolver();
        assertTrue(solver.solve(board));
    }

    @Test
    void boardHasGivenEmptyCells() {
        RandomGenerator generator = new RandomGenerator();
        int expectedEmptyCount = 40;
        Board board = generator.generate(expectedEmptyCount);

        int emptyCount = 0;
        for (int r = 0; r < Board.BOARD_SIZE; r++) {
            for (int c = 0; c < Board.BOARD_SIZE; c++) {
                if (board.isEmpty(r, c)) {
                    emptyCount++;
                }
            }
        }

        assertEquals(expectedEmptyCount, emptyCount);
    }
}