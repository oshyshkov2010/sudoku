package com.oshyshkov.games;

import java.util.*;

public class Board {
    public static final Character[] NUMBERS = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static Set<Character> VALID_NUMBERS = new HashSet<>(Arrays.asList(NUMBERS));
    public static final int BOARD_SIZE = 9;
    public static final int BOX_SIZE = 3;
    public static final int LAST_COLUMN = BOARD_SIZE - 1;

    private Cell[][] board;
    private int emptyCells;

    private Board() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        emptyCells = BOARD_SIZE * BOARD_SIZE;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = new Cell(row, col, Cell.EMPTY, false);
            }
        }
    }

    public Board(List<Cell> startCells) {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        emptyCells = BOARD_SIZE * BOARD_SIZE;

        for (Cell cell : startCells) {
            assert(!cell.isEmpty());
            board[cell.getRow()][cell.getColumn()] =
                    new Cell(cell.getRow(), cell.getColumn(), cell.getNumber(), true);
            emptyCells--;
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == null) {
                    board[row][col] = new Cell(row, col, Cell.EMPTY, false);
                }
            }
        }
    }

    public char getNumber(int row, int col) {
        return board[row][col].getNumber();
    }

    public boolean isImmutable(int row, int col) {
        return board[row][col].isImmutable();
    }

    public void setNumber(int row, int col, char number) {
        assert(number != Cell.EMPTY);
        if (board[row][col].isImmutable()) {
            return;
        }

        board[row][col].setNumber(number);
        emptyCells--;
    }

    public void setEmpty(int row, int column) {
        board[row][column].setNumber(Cell.EMPTY);
        emptyCells++;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col].isEmpty();
    }

    boolean isSolved() {
        return emptyCells == 0;
    }

    public int emptyCellsCount() {
        return emptyCells;
    }

    public char[][] toCharView() {
        char[][] array = new char[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                array[row][col] = board[row][col].getNumber();
            }
        }

        return array;
    }

    public Set<Cell> getNonEmptyDuplicates() {
        Set<Cell> allDuplicates = new HashSet<>();
        allDuplicates.addAll(rowDuplicates());
        allDuplicates.addAll(columnDuplicates());
        allDuplicates.addAll(boxDuplicates());

        return allDuplicates;
    }

    private Set<Cell> rowDuplicates() {
        Set<Cell> duplicates = new HashSet<>();

        for (int row = 0; row < board.length; row++) {
            Map<Character, Cell> visited = new HashMap<>();
            for (int col = 0; col < board[row].length; col++) {
                checkDuplicate(duplicates, board[row][col], visited);
            }
        }

        return duplicates;
    }

    private Set<Cell> columnDuplicates() {
        Set<Cell> duplicates = new HashSet<>();

        for (int col = 0; col < board.length; col++) {
            Map<Character, Cell> visited = new HashMap<>();
            for (int row = 0; row < board[col].length; row++) {
                checkDuplicate(duplicates, board[row][col], visited);
            }
        }

        return duplicates;

    }

    private Set<Cell> boxDuplicates() {
        Set<Cell> duplicates = new HashSet<>();

        int regionSize = BOARD_SIZE / BOX_SIZE;
        for (int regionRow = 0; regionRow < regionSize; regionRow++) {
            for (int regionCol = 0; regionCol < regionSize; regionCol++) {
                int rowStart = regionRow * regionSize;
                int colStart = regionCol * regionSize;

                Map<Character, Cell> visited = new HashMap<>();
                for (int col = colStart; col < BOX_SIZE; col++) {
                    for (int row = rowStart; row < BOX_SIZE; row++) {
                        checkDuplicate(duplicates, board[row][col], visited);
                    }
                }
            }
        }

        return duplicates;
    }

    private void checkDuplicate(Set<Cell> duplicates, Cell cell, Map<Character, Cell> visited) {
        if (visited.containsKey(cell.getNumber())) {
            Cell duplicate = visited.get(cell.getNumber());
            duplicates.add(duplicate);
            duplicates.add(cell);
        }
        if (!cell.isEmpty()) {
            visited.put(cell.getNumber(), cell);
        }
    }

    public boolean isValidAssignment(int row, int col, char number) {
        return !hasRowValue(row, number)
                && !hasColumnValue(col, number)
                && !hasBoxValue(row - row % BOX_SIZE,
                col - col % BOX_SIZE, number);
    }

    private boolean hasRowValue(int row, char number) {
        for (int col = 0; col < board[row].length; col++) {
            if (number == board[row][col].getNumber()) {
                return true;
            }
        }

        return false;
    }

    private boolean hasColumnValue(int col, char number) {
        for (int row = 0; row < board.length; row++) {
            if (number == board[row][col].getNumber()) {
                return true;
            }
        }

        return false;
    }

    private boolean hasBoxValue(int startRow, int startColumn, char number) {
        for (int row = startRow; row < startRow + BOX_SIZE; row++) {
            for (int col = startColumn; col < startColumn + BOX_SIZE; col++) {
                if (number == board[row][col].getNumber()) {
                    return true;
                }
            }
        }

        return false;
    }

    public Set<Character> getCandidateValues(int row, int col) {
        Set<Character> candidates = new HashSet<>(VALID_NUMBERS);

        for (int r = 0; r < BOARD_SIZE; r++) {
            if (!board[r][col].isEmpty()) {
                candidates.remove(board[r][col].getNumber());
            }
        }

        for (int c = 0; c < BOARD_SIZE; c++) {
            if (!board[row][c].isEmpty()) {
                candidates.remove(board[row][c].getNumber());
            }
        }

        int rowStart = row - row % BOX_SIZE;
        int colStart = col - col % BOX_SIZE;
        for (int r = rowStart; r < rowStart + BOX_SIZE; r++) {
            for (int c = colStart; c < colStart + BOX_SIZE; c++) {
                if (!board[r][c].isEmpty()) {
                    candidates.remove(board[r][c].getNumber());
                }
            }
        }

        return candidates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                sb.append(board[row][col].getNumber());
            }
        }

        return sb.toString();
    }

    public static Board emptyBoard() {
        return new Board();
    }

    public static Board fromString(String board) {
        List<Cell> cells = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int cellIndex = row * BOARD_SIZE + col;
                char cellNumber = board.charAt(cellIndex);
                if (cellNumber != Cell.EMPTY) {
                    Cell cell = new Cell(row, col, board.charAt(cellIndex), true);
                    cells.add(cell);
                }
            }
        }

        return new Board(cells);
    }
}
