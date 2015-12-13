package entity;

import java.awt.Graphics2D;

import utility.DrawingUtility;
import utility.ScreenSize;

public class FThrow extends Enemy {

	public FThrow(int x, int y, int z, int speed) {
		super(x, y, z, speed);
		// TODO Auto-generated constructor stub
		width = 78;
		height = 90;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawF(g2d, x, y);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		x += speed;
		if(outOfBound()) {
			hitWithPlayer();
		}
		if(x<0) destroyed = true;
	}

	@Override
	public void hitWithPlayer() {
		// TODO Auto-generated method stub
		Player.setStressLevel(80);
		destroyed = true;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		speed = -20;
		tempspeed = -20;
		Player.setScore(Player.getScore()+100);
		clicked = true;
	}

}
