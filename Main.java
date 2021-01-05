class Main {
    public static void main(String[] args) {
        /*
            The javadoc comments exist so I can track what function returns what in VSCode, since thats where this was written
            If you're viewing this in Repl.it, it sadly does not support javadoc for some odd reason
        */

        System.out.println("\nWelcome to Tom's Tic-Tac-Toe Engine. Tic-Tac-Toe is drawn by force, but let's see if you can win!");

        Game newGame = new Game();

        newGame.Start();
    }
}