# TICTACTOE ENGINE
A simple tictactoe engine with the goal of drawing or winning every game

Best Squares: corners -> middle -> sides

## General Strategy
There is an opening trap within tictactoe, if player 1 plays corner and player 2 does not take the middle immediately, the engine needed to be able to respond to this

The engine needs to take the best squares every time. First, the engine checks if any player can win. Second, if it is the first move, the computer checks if the middle is open (to avoid the opening trap) Third, it checks if the corners are open. Fourth, if only the sides are left, it picks a random side (because there are no wins on the board).

## Code Structure
All engine code is located in Engine.java
Board.java controls the actual board rendering and where everything is on the board. It currently uses a 2d array for piece storage
Position.java is a wrapper for position data so we don't have complicated arrays
Game.java handles the actual interaction between a player and the program