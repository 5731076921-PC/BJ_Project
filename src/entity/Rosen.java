package entity;

import java.awt.Graphics2D;

import utility.DrawingUtility;
import utility.ScreenSize;

public class Rosen extends Enemy {
	private int upperBound;
	private int lowerBound;
	private int movingDirection;
	private final int UP_DIRECTION = -1;
	private final int DOWN_DIRECTION = 1;

	public Rosen(int x, int y, int z, int speed) {
		super(x, y, z, speed);
		// TODO Auto-generated constructor stub
		movingDirection = UP_DIRECTION;
		width = 216;
		height = 117;
		upperBound = y-100;
		if(upperBound<0) upperBound =0;
		lowerBound = y;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	public boolean isBouncing() {
		return y > lowerBound || y < upperBound;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawRosen(g2d, x, y);
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
		if (isBouncing()) {
			movingDirection = -movingDirection;
		}
		x += speed;
		y += speed * movingDirection;
		if(outOfBound()) {
			hitWithPlayer();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void hitWithPlayer() {
		// TODO Auto-generated method stub
		Player.setStressLevel(Player.getStressLevel() + 35);
		destroyed = true;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		movingDirection = 0;
		speed = -20;
		tempspeed = -20;
		Player.setScore(Player.getScore()+250);
		clicked = true;
	}

}