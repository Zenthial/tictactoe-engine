class Main {
    public static void main(String[] args) {
        /*
            The javadoc comments exist so I can track what function returns what in VSCode, since thats where this was written
            If you're viewing this in Repl.it, it sadly does not support javadoc for some odd reason
        */

        Board gameBoard = new Board();

        System.out.println(gameBoard.SetTile("X", 1, 2));
        gameBoard.RenderBoard();
    }
}