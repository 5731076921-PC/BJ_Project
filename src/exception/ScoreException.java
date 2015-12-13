package exception;

import entity.Player;

public class ScoreException extends Exception {
	public ScoreException() {
		System.out.println("Score can't less than zero.");
	}

}
