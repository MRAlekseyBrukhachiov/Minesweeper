package minesweeper;

import java.util.Random;

public class GameField {

    private int width = 9;
    private int height = 9;
    private int mines;
    private boolean isOver = false;
    private int researchedCells = 0;
    private Cell[][] field;

    public GameField (int mines){
        this.mines = mines;
        field = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = new Cell();
            }
        }
        for (int i = 0; i < mines; i++) {
            this.setRandomMine();
        }
        setCountMinesInCell();
    }

    public void print() {
        System.out.println(" |123456789|\n-|---------|");

        for (int i = 0; i < width; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < height; j++) {
                if (field[i][j].isResearched()) {
                    if (field[i][j].isEmpty()){
                        System.out.print("/");
                    } else if (field[i][j].isMined()){
                        System.out.print("X");
                    } else {
                        System.out.print(field[i][j].getValue());
                    }
                } else {
                    if (field[i][j].isMarked()) {
                        System.out.print("*");
                    } else {
                        System.out.print(".");
                    }
                }
            }
            System.out.println("|");
        }

        System.out.println("-|---------|");
    }

    public void setRandomMine() {
        Random random = new Random();
        int row = random.nextInt(9);
        int col = random.nextInt(9);

        if (field[row][col].isEmpty()) {
            field[row][col].mined();
        } else {
            setRandomMine();
        }
    }

    private boolean outOfBounds(int row, int col) {
        try {
            Cell cell = field[row][col];
            return false;
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
    }

    public int countMineAround(int row, int col) {
        int mines = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!outOfBounds(row + i, col + j)
                        && field[row + i][col + j].isMined()) {
                    mines++;
                }
            }
        }
        return mines;
    }

    private void setCountMinesInCell() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (field[i][j].isEmpty()) {
                    field[i][j].setValue(countMineAround(i, j));
                }
            }
        }
    }

    public Cell[][] getField() {
        return field;
    }

    public int getMines() {
        return mines;
    }

    public void research(int row, int col) {
        if (field[row][col].isResearched() || field[row][col].isMined()) {
            return;
        }

        field[row][col].setResearched(true);
        researchedCells++;

        if (!outOfBounds(row - 1, col)) {
            research(row - 1, col);
        }
        if (!outOfBounds(row, col + 1)) {
            research(row, col + 1);
        }
        if (!outOfBounds(row + 1, col)) {
            research(row + 1, col);
        }
        if (!outOfBounds(row, col - 1)) {
            research(row, col - 1);
        }
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public int getResearchedCells() {
        return researchedCells;
    }
}
