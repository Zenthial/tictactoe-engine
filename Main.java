class Main {
    public static void main(String[] args) {
        Board gameBoard = new Board();

        System.out.println(gameBoard.SetTile("X", 1, 2));
        gameBoard.RenderBoard();
    }
}