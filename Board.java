public class Board {
    // "" - square unfilled, "X" - X, "O" - O
    private int rows = 3;
    private int columns = 3;
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

    public boolean SetTile(String letter, int x, int y) {
        if ((this.boardState[y][x]).equals("")) {
            this.boardState[y][x] = letter;
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
    }
}
