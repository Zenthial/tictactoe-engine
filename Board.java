public class Board {
    // "" - square unfilled, "X" - X, "O" - O
    private int rows = 3;
    private int columns = 3;
    private int moves = 0;
    private String[][] boardState = new String[columns][rows];;

    Board() {
        // initalize our board state
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                this.boardState[i][j] = "";
            }
        }
        System.out.println("New board created!");
    }
    
    public String[][] GetBoard() {
        return this.boardState;
    }

    // instead of returning a complicated array of multiple smaller arrays, we just use a simple position class which stores
    // position information, that way we can easily return a position array with specific information
    public Position[] GetOpenSquares() {
        int openSquaresLeft = (columns*rows) - moves;
        Position[] openSquares = new Position[openSquaresLeft];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (this.boardState[i][j].equals("")) {
                    Position openSquare = new Position(i, j);
                    openSquaresLeft--;
                    if (openSquaresLeft >= 0) {
                        openSquares[openSquaresLeft] = openSquare;
                    }
                }
            }
        }

        return openSquares;
    }

    public boolean SetTile(String letter, int x, int y) {
        if ((this.boardState[y][x]).equals("")) {
            this.boardState[y][x] = letter;
            this.moves++;
            return true;
        } else {
            return false;
        }
    }

    public String GetTile(int x, int y) {
        return this.boardState[y][x];
    }

    public void RenderBoard() {
        System.out.print("\t0\t1\t2\n\n0\t");
        for (int i = 0; i < rows; i++) {
            System.out.print(this.boardState[0][i] + "\t");
        }
        System.out.print("\n\n1\t");
        for (int i = 0; i < rows; i++) {
            System.out.print(this.boardState[1][i] + "\t");
        }
        System.out.print("\n\n2\t");
        for (int i = 0; i < rows; i++) {
            System.out.print(this.boardState[2][i] + "\t");
        }
        System.out.print("\n");
    }
}
