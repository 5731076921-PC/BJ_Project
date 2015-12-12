package entity;

import java.awt.Graphics2D;

import Utility.DrawingUtility;
import Utility.InputUtility;
import Utility.ScreenSize;

public class QuestionMark extends Enemy {	
	private int direction;
	private int directionTick;
	public QuestionMark(int x, int y, int z, int speed) {
		super(x, y, z, speed);
		direction =1;
		directionTick = 0;
		width = 86;
		height = 162;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawQuestionMarkItem(g2d, x, direction);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !destroyed;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		directionTick++;
		if(directionTick >= 8) {
		direction = -direction;
			directionTick = 0;
		}
		
		x += speed;
		if(x<0) destroyed = true;
		if(outOfBound()) {
			hitWithPlayer();
		}

	}

	@Override
	public void hitWithPlayer() {
		// TODO Auto-generated method stub
		Player.setStressLevel(Player.getStressLevel() + 10);
		destroyed = true;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		speed = -20;
		tempspeed = -20;
		clicked = true;
		Player.setScore(Player.getScore()+150);
	}



}
