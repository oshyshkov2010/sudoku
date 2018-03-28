package com.oshyshkov.games;

public class Cell {
    public static char EMPTY = '0';

    private int row;
    private int column;
    private char number;
    private boolean isImmutable;

    public Cell(int row, int col, char number, boolean isImmutable) {
        this.row = row;
        this.column = col;
        this.number = number;
        this.isImmutable = isImmutable;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getNumber() {
        return number;
    }

    public void setNumber(char number) {
        this.number = number;
    }

    public boolean isImmutable() {
        return isImmutable;
    }

    public boolean isEmpty() {
        return number == EMPTY;
    }

    @Override
    public String toString() {
        return "Row: " + row + " Column: " + column + " = " + number;
    }
}
