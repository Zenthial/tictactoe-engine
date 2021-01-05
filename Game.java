import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner input;

    Game() {
        this.board = new Board();
        this.input = new Scanner(System.in);
    }
    
    public void Start() {
        int gameMoves = 0;
        boolean lastPlayerMove = false; // false for Os, true for Xs
        while (gameMoves < 9) {
            this.board.RenderBoard();
            if (this.board.CheckWin() > -1)
                break;
            if (!lastPlayerMove) {
                GetPlayerMove();
            } else {
                GetComputerMove(board, gameMoves);
            }
            lastPlayerMove = !lastPlayerMove;
            gameMoves++;
        }
        
        if (this.board.CheckWin() == 0)
            System.out.println("Os have won!");
        if (this.board.CheckWin() == 1)
            System.out.println("Xs have won!");
        if (this.board.CheckWin() == -1)
            System.out.println("It's a draw!");

        this.board.RenderBoard();
    }
    
    /**Recursive until the player plays a move that isn't occupied */
    private void GetPlayerMove() {
        System.out.println("Please enter your x axis move");
        int x = input.nextInt();
        System.out.println("Please enter your y axis move");
        int y = input.nextInt();

        if (!board.SetTile("X", x, y)) {
            System.out.println("That square is occupied");
            GetPlayerMove();
        }
    }

    private void GetComputerMove(Board board, int moveNum) {
        Engine.PlayBestMove(board, moveNum);
    }
}
