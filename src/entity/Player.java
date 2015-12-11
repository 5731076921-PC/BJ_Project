package entity;

import java.awt.Graphics2D;

import org.w3c.dom.Entity;

import render.IRenderable;

public class Player implements IRenderable{

private static  int score;
private static int level;
private static int stressLevel;
private static boolean pause;

	public Player() {
		super();
		this.score = 0;
		this.level = 1;
		this.stressLevel = 0;
	}
	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Player.score = score;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Player.level = level;
	}

	public static int getStressLevel() {
		return stressLevel;
	}

	public static void setStressLevel(int stressLevel) {
		if(stressLevel >100) {
			Player.stressLevel =0;
			level++;
		}
		else Player.stressLevel = stressLevel;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	public static boolean isPause() {
		return pause;
	}

	public static void setPause(boolean pause) {
		Player.pause = pause;
	}



}
