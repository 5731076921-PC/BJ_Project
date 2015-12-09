package entity;

import java.awt.Graphics2D;

import Utility.ScreenSize;

public class Rosen extends Enemy {
	private int upperBound = 0;
	private int lowerBound = ScreenSize.HEIGHT;
	private int movingDirection;
	private final int UP_DIRECTION = -1;
	private final int DOWN_DIRECTION = 1;
	public Rosen(int x, int y, int z, int speed) {
		super(x, y, z, speed);
		// TODO Auto-generated constructor stub
		movingDirection = UP_DIRECTION;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean isBouncing() {
		return y>=lowerBound || y <= upperBound;
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
		return !destroyed;
	}

	@Override
	public void move() {
		if(isBouncing()) {
			movingDirection = -movingDirection;
		}
		x += speed;
		y += speed*movingDirection;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hitWithPlayer() {
		// TODO Auto-generated method stub
		destroyed = true;
		Player.setStressLevel(Player.getStressLevel() + 35);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		movingDirection =0;
		speed = -50;
		
	}

}
