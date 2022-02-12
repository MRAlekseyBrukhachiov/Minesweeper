package minesweeper;

public class Cell {

    private final int CELL_MINE = 10;
    private final int CELL_EMPTY = 0;
    private int value;
    private boolean isMarked;
    private boolean isResearched;

    public Cell() {
        this.value = 0;
        this.isMarked = false;
        this.isResearched = false;
    }

    public int getValue() {
        return value;
    }

    public void mined() {
        this.value = CELL_MINE;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public boolean isMined() {
        return value == CELL_MINE;
    }

    public boolean isEmpty() {
        return value == CELL_EMPTY;
    }

    public boolean isResearched() {
        return isResearched;
    }

    public void setResearched(boolean researched) {
        isResearched = researched;
    }
}
