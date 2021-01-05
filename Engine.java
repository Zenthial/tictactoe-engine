public class Engine {
    public static void GetBestMove(Board board, int lastX, int lastY, int moveNum) {
        // implement determining the best move logic
    }

    private static boolean TryMove(Board board, int x, int y) {
        return board.SetTile("O", x, y);
    }

    private static int[] BookMoves(Board board, int lastX, int lastY) {
        if ((lastX != 1 || lastY != 1) && board.GetTile(1, 1) == "") {
            return new int[] {1, 1};
        }
    }

    // returns -1 if there are no Columns with a win, returns 0-2 if there are
    private static int CheckIfOpponentWinOnRows(Board board) {
        for (int i = 0; i < 3; i++) {
            int numOfXs = 0;
            for (int j = 0; j < 3; j++) {
                // flips from the columns
                String tile = board.GetTile(i, j);
                if (tile.equals("X")) {
                    numOfXs++;
                }
            }

            if (numOfXs == 2) {
                return i;
            }
        }

        return -1;
    }

    // returns -1 if there are no Columns with a win, returns 0-2 if there are
    private static int CheckIfOpponentWinOnColumns(Board board) {
        for (int i = 0; i < 3; i++) {
            int numOfXs = 0;
            int j;
            for (j = 0; j < 3; j++) {
                // flips from the rows
                String tile = board.GetTile(j, i);
                if (tile.equals("X")) {
                    numOfXs++;
                }
            }

            if (numOfXs == 2) {
                return j;
            }
        }

        return -1;
    }

    // returns -1 if there are no Columns with a win, returns 0 if its on the 0-0, 1-1, 2-2 diag, 1 if its on the 0-2, 1-1, 2-0 diag
    private static int CheckIfOpponentWinOnDiagonals(Board board) {
        for (int i = 0; i < 3; i++) {
            int numOfXs = 0;
            String tile = board.GetTile(i, i);
            if (tile.equals("X")) {
                numOfXs++;
            }

            if (numOfXs == 2) {
                return 0;
            }
        }

        int startingX = 2;
        for (int i = 0; i < 3; i++) {
            int numOfXs = 0;
            String tile = board.GetTile(i, startingX);
            startingX--;
            if (tile.equals("X")) {
                numOfXs++;
            }

            if (numOfXs == 2) {
                return 1;
            }
        }

        return -1;
    }
}
