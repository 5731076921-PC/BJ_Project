package entity;
import java.awt.Graphics2D;

import org.w3c.dom.Entity;

import exception.GameOverException;
import render.IRenderable;

public class Player {

private static  int score;
private static int level = 1;
private static int stressLevel;
private static boolean pause;
private static boolean gameOver;
private static int tempScore;
	public Player() {
		super();
		this.score = 0;
		this.stressLevel = 0;
	}
	public static int getScore() {
		return score;
	}
	public static void clear() {
		tempScore=score;
		score=0;
		level=1;
		pause=false;
		gameOver=false;
		stressLevel =0;
	}
	public static void setScore(int score) {
		Player.score = score;
		if(Player.score <0) {
			Player.score=0;
		}
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) throws GameOverException {
		Player.level = level;
		if(level>3) throw new GameOverException();
		else if(level<0) Player.level =0;
	}

	public static int getStressLevel() {
		return stressLevel;
	}

	public static void setStressLevel(int stressLevel) {
		if(stressLevel >100) {
			Player.stressLevel =0;
				try {
					setLevel(getLevel()+1);
				} catch (GameOverException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					setStressLevel(100);
					gameOver = true;
				}

		}
		else if(stressLevel < 0) 
			Player.stressLevel = 0; 
		else 
			Player.stressLevel = stressLevel;
	}

	public static boolean isPause() {
		return pause;
	}

	public static void setPause(boolean pause) {
		Player.pause = pause;
	}
	public static boolean isGameOver() {
		return gameOver;
	}
	public static int getTempScore() {
		return tempScore;
	}




}
