package exception;

import entity.Player;

public class GameOverException extends Exception {
	public GameOverException() {
		System.out.println("The game is over.");
		System.out.println("Your score is " +Player.getScore());
	}

}
