package minesweeper;

import java.util.Scanner;

public class Main {

    private static int foundMines = 0;

    public static void inputCell(GameField gameField) {
        final String FREE = "free";
        final String MINE = "mine";
        Scanner in = new Scanner(System.in);
        System.out.println("Set/unset mine marks or claim a cell as free: ");

        int row = in.nextInt();
        int col = in.nextInt();
        String move = in.next();
        Cell[][] field = gameField.getField();
        Cell cell = field[col - 1][row - 1];

        if (move.equals(MINE)) {
            if (cell.isMarked()) {
                cell.setMarked(false);
                foundMines--;
            } else {
                cell.setMarked(true);
                foundMines++;
            }
        } else if (move.equals(FREE)) {
            if (cell.isMined()) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (field[i][j].isMined()) {
                            field[i][j].setResearched(true);
                        }
                    }
                }
                gameField.setOver(true);
            } else {
                gameField.research(col - 1, row - 1);
            }
        } else {
            System.out.println("Incorrect input. Try again!");
            inputCell(gameField);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many mines do you want on the field? ");
        int mines = in.nextInt();
        GameField gameField = new GameField(mines);
        boolean win = false;

        while (!gameField.isOver()) {
            gameField.print();
            inputCell(gameField);
            if (foundMines == mines || mines == 81 - gameField.getResearchedCells()) {
                gameField.setOver(true);
                win = true;
            }
        }

        gameField.print();

        if (win) {
            System.out.println("Congratulations! You found all the mines!");
        } else {
            System.out.println("You stepped on a mine and failed!");
        }
    }
}
