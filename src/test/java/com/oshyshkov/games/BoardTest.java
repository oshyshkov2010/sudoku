package com.oshyshkov.games;

import com.oshyshkov.games.Board;
import com.oshyshkov.games.Cell;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private static final char NUMBER = '1';
    private static final int ROW = 1;
    private static final int DUPLICATE_ROW = 2;
    private static final int COLUMN = 1;
    private static final int DUPLICATE_COLUMN = 2;

    @Test
    void rowDuplicates() {
        Board board = new Board(Collections.emptyList());

        board.setNumber(ROW, COLUMN, NUMBER);
        board.setNumber(ROW, DUPLICATE_COLUMN, NUMBER);

        Set<Cell> duplicates =  board.getNonEmptyDuplicates();

        assertEquals(2, duplicates.size());
    }

    @Test
    void columnDuplicates() {
        Board board = new Board(Collections.emptyList());

        board.setNumber(ROW, COLUMN, NUMBER);
        board.setNumber(DUPLICATE_ROW, COLUMN, NUMBER);

        Set<Cell> duplicates =  board.getNonEmptyDuplicates();

        assertEquals(2, duplicates.size());
    }

    @Test
    void squareDuplicates() {
        Board board = new Board(Collections.emptyList());

        board.setNumber(ROW, COLUMN, NUMBER);
        board.setNumber(DUPLICATE_ROW, DUPLICATE_COLUMN, NUMBER);

        Set<Cell> duplicates =  board.getNonEmptyDuplicates();

        assertEquals(2, duplicates.size());
    }

    @Test
    void candidateBoxTest() {
        Board board = Board.fromString(
                        "000000000" +
                        "000000000" +
                        "000000000" +
                        "000000000" +
                        "000789000" +
                        "000456000" +
                        "000000000" +
                        "000000000" +
                        "000000000");

        Set<Character> expected = new HashSet<>(Arrays.asList('1', '2', '3'));
        assertEquals(expected, board.getCandidateValues(3, 3));
        assertEquals(expected, board.getCandidateValues(3, 4));
        assertEquals(expected, board.getCandidateValues(3, 5));
    }

    @Test
    void candidateColumnTest() {
        Board board = Board.fromString(
                        "000000000" +
                        "010000000" +
                        "020000000" +
                        "030000000" +
                        "040000000" +
                        "050000000" +
                        "060000000" +
                        "070000000" +
                        "000000000");

        Set<Character> expected = new HashSet<>(Arrays.asList('8', '9'));
        assertEquals(expected, board.getCandidateValues(0, 1));
        assertEquals(expected, board.getCandidateValues(8, 1));
    }

    @Test
    void candidateRowTest() {
        Board board = Board.fromString(
                "000000000" +
                "010234567" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000");

        Set<Character> expected = new HashSet<>(Arrays.asList('8', '9'));
        assertEquals(expected, board.getCandidateValues(1, 0));
        assertEquals(expected, board.getCandidateValues(1, 2));
    }
}