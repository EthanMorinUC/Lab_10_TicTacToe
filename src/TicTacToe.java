import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] BOARD = new String[ROWS][COLS];
    private static int currentPlayer = 1; // 1 for Player 1 (X), 2 for Player 2 (O)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            clearBoard();
            int moveCounter = 0;

            while (true) {
                display();

                int rowMove = SafeInput.getRangedInt(scanner, "Enter row (1, 2, or 3) for player " + currentPlayer, 1, 3) - 1;
                int colMove = SafeInput.getRangedInt(scanner, "Enter column (1, 2, or 3) for player " + currentPlayer, 1, 3) - 1;

                while (!isValidMove(rowMove, colMove)) {
                    System.out.println("This move is not valid. Try again.");
                    rowMove = SafeInput.getRangedInt(scanner, "Enter row (1, 2, or 3) for player " + currentPlayer, 1, 3) - 1;
                    colMove = SafeInput.getRangedInt(scanner, "Enter column (1, 2, or 3) for player " + currentPlayer, 1, 3) - 1;
                }

                if (currentPlayer == 1) {
                    BOARD[rowMove][colMove] = "X";
                } else {
                    BOARD[rowMove][colMove] = "O";
                }
                moveCounter++;

                String playerSymbol = (currentPlayer == 1) ? "X" : "O";

                if (moveCounter >= 5) {
                    if (isWin(playerSymbol)) {
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    }
                }

                if (moveCounter == 9 || isTie()) {
                    display();
                    System.out.println("The game is a draw!");
                    break;
                }

                switchPlayer();
            }

            boolean playAgain = SafeInput.getYNConfirm(scanner, "Would you like to play again?");
            if (!playAgain) {
                break;
            }
        } while (true);

        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                BOARD[i][j] = " ";
            }
        }
        currentPlayer = 1; // Player 1 (X) starts
    }

    private static void display() {
        System.out.println("   1  2  3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(BOARD[i][j]);
                if (j < COLS - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("  ---------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return BOARD[row][col].equals(" ");
    }

    private static void switchPlayer() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (BOARD[i][0].equals(player) && BOARD[i][1].equals(player) && BOARD[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (BOARD[0][i].equals(player) && BOARD[1][i].equals(player) && BOARD[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (BOARD[0][0].equals(player) && BOARD[1][1].equals(player) && BOARD[2][2].equals(player)) ||
                (BOARD[0][2].equals(player) && BOARD[1][1].equals(player) && BOARD[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (BOARD[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return !isWin("X") && !isWin("O");
    }
}
