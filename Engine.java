public class Engine {
    public static Position[] corners = new Position[] {new Position(0, 0), new Position(0, 2), new Position(2, 0), new Position(2, 2)};
    public static Position[] sides = new Position[] {new Position(0, 1), new Position(2, 1), new Position(1, 0), new Position(1, 2)};
    public static Position middle = new Position(1, 1);

    public static void PlayBestMove(Board board, int lastX, int lastY, int moveNum) {
        if (moveNum == 1) {
            // returned in the form [x,y]
            int[] bookMove = BookMoves(board);
            // will always return true as it knows where the only move is
            TryMove(board, bookMove[0], bookMove[1]);
        } else {
            int computerRows = CheckIfComputerWinOnRows(board);
            int computerColumns = CheckIfComputerWinOnColumns(board);
            int computerDiags = CheckIfComputerWinOnDiagonals(board);

            int opponentRows = CheckIfOpponentWinOnRows(board);
            int opponentColumns = CheckIfOpponentWinOnColumns(board);
            int opponentDiags = CheckIfOpponentWinOnDiagonals(board);


            // see if theres a better solution than a giant chained if statement, possibly switch statement?
            if (computerRows > -1) {
                for (int i = 0; i < 3; i++) {
                    if (TryMove(board, computerRows, i)) {
                        break;
                    }
                }
            } else if (computerColumns > -1) {
                for (int i = 0; i < 3; i++) {
                    if (TryMove(board, i, computerColumns)) {
                        break;
                    }
                }
            } else if (computerDiags > -1) {
                // 0-0 -> 2-2 diag
                if (computerDiags == 0) {
                    for (int i = 0; i < 3; i++) {
                        if (TryMove(board, i, i)) {
                            break;
                        }
                    }
                // 0-2 -> 2-0 diag
                } else if (computerDiags == 1) {
                    int x = 2;
                    for (int i = 0; i < 3; i++) {
                        if (TryMove(board, i, x)) {
                            break;
                        }
                        x--;
                    }
                }
            } else if (opponentRows > -1) {
                for (int i = 0; i < 3; i++) {
                    if (TryMove(board, opponentRows, i)) {
                        break;
                    }
                }
            } else if (opponentColumns > -1) {
                for (int i = 0; i < 3; i++) {
                    if (TryMove(board, i, opponentColumns)) {
                        break;
                    }
                }
            } else if (opponentDiags > -1) {
                // 0-0 -> 2-2 diag
                if (opponentDiags == 0) {
                    for (int i = 0; i < 3; i++) {
                        if (TryMove(board, i, i)) {
                            break;
                        }
                    }
                // 0-2 -> 2-0 diag
                } else if (opponentDiags == 1) {
                    int x = 2;
                    for (int i = 0; i < 3; i++) {
                        if (TryMove(board, i, x)) {
                            break;
                        }
                        x--;
                    }
                }
            } else {
                // no possible wins on the board, lets just take a square
                int[] bookMove = BookMoves(board);
                TryMove(board, bookMove[0], bookMove[1]);
            }
        }
    }

    /** @return boolean if the move was able to be played or not */
    private static boolean TryMove(Board board, int x, int y) {
        return board.SetTile("O", x, y);
    }

    /** @return in x, y form */
    private static int[] BookMoves(Board board) {

        /*  during my research, i found theres a forced win if the second player doesnt take the middle on their first move
            so if they dont take the middle (which the best move is to take a corner), the computer needs to take it
        */
        if (board.GetTile(1, 1) == "") {
            return new int[] { 1, 1 };
        }
        
        // corners are better than sides, so check if any corners are open
        for (Position corner : corners) {
            int[] coordinate = corner.Get();
            if (board.GetTile(coordinate[0], coordinate[1]).equals("")) {
                return coordinate;
            }
        }
        
        // sides suck but if there arent any corners, lets place a piece on the side
        for (Position side : sides) {
            int[] coordinate = side.Get();
            if (board.GetTile(coordinate[0], coordinate[1]).equals("")) {
                return coordinate;
            }
        }
        
        // somehow there aren't any available squares but the game hasn't ended?
        return new int[] { -1, -1 };
    }
    
    /** @return -1 if there are no Columns with a win, returns 0-2 if there are, with the row number */
    private static int CheckIfComputerWinOnRows(Board board) {
        for (int i = 0; i < 3; i++) {
            int numOfOs = 0;
            for (int j = 0; j < 3; j++) {
                // flips from the columns
                String tile = board.GetTile(i, j);
                if (tile.equals("O")) {
                    numOfOs++;
                }
            }

            if (numOfOs == 2) {
                return i;
            }
        }

        return -1;
    }

    /** @return -1 if there are no Columns with a win, returns 0-2 if there are, with the column number */
    private static int CheckIfComputerWinOnColumns(Board board) {
        for (int i = 0; i < 3; i++) {
            int numOfOs = 0;
            int j;
            for (j = 0; j < 3; j++) {
                // flips from the rows
                String tile = board.GetTile(j, i);
                if (tile.equals("O")) {
                    numOfOs++;
                }
            }

            if (numOfOs == 2) {
                return j;
            }
        }

        return -1;
    }

    /** @return -1 if no columns, 0 if its on the 0-0 to 2-2 diag, 1 if its on the 0-2 to 2-0 diag*/
    private static int CheckIfComputerWinOnDiagonals(Board board) {
        for (int i = 0; i < 3; i++) {
            int numOfOs = 0;
            String tile = board.GetTile(i, i);
            if (tile.equals("O")) {
                numOfOs++;
            }

            if (numOfOs == 2) {
                return 0;
            }
        }

        int startingX = 2;
        for (int i = 0; i < 3; i++) {
            int numOfXs = 0;
            String tile = board.GetTile(i, startingX);
            startingX--;
            if (tile.equals("O")) {
                numOfXs++;
            }

            if (numOfXs == 2) {
                return 1;
            }
        }

        return -1;
    }

    /** @return -1 if there are no Columns with a win, returns 0-2 if there are, with the row number */
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

    /** @return -1 if there are no Columns with a win, returns 0-2 if there are, with the column number */
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

    /** @return -1 if no columns, 0 if its on the 0-0 to 2-2 diag, 1 if its on the 0-2 to 2-0 diag*/
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
