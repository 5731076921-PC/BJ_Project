package entity;
import java.awt.Graphics2D;

import org.w3c.dom.Entity;

import render.IRenderable;

public class Player {

private static  int score;
private static int level = 1;
private static int stressLevel;
private static boolean pause;

	public Player() {
		super();
		this.score = 0;
		this.stressLevel = 0;
	}
	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Player.score = score;
		if(score<0) Player.score =0;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Player.level = level;
		if(level>3) Player.level =3;
		else if(level<0) Player.level =0;
	}

	public static int getStressLevel() {
		return stressLevel;
	}

	public static void setStressLevel(int stressLevel) {
		if(stressLevel >100) {
			Player.stressLevel =0;
			if(level<3) {
				level++;
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



}
