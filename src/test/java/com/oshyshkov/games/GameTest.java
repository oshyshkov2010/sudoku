package com.oshyshkov.games;

import com.oshyshkov.games.Board;
import com.oshyshkov.games.Cell;
import com.oshyshkov.games.Game;
import com.oshyshkov.games.Result;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static final char NUMBER = '1';
    private static final int ROW = 0;
    private static final int COLUMN = 0;
    private static final int DUPLICATE_COLUMN = 1;

    @Test
    void mutableDuplicatesOnly() {
        List<Cell> initialList = Arrays.asList(new Cell(ROW,COLUMN, NUMBER, true));
        Board board = new Board(initialList);

        Game game = new Game(board);

        Result result = game.setNumber(ROW, DUPLICATE_COLUMN, NUMBER);
        List<Cell> errors = result.getErrors();

        Cell errorCell = errors.iterator().next();
        assertEquals(1, errors.size());
        assertEquals(DUPLICATE_COLUMN, errorCell.getColumn());
        assertFalse(errorCell.isImmutable());
    }
}