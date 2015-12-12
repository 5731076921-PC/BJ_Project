package entity;

import java.awt.Graphics2D;

import Utility.DrawingUtility;
import Utility.ScreenSize;

public class SleepyDrug extends Enemy {
	private int speedSlow;

	public SleepyDrug(int x, int y, int z, int speed, int speedSlow) {
		super(x, y, z, speed);
		// TODO Auto-generated constructor stub
		this.speedSlow = speedSlow;
		this.width = 190;
		this.height = 67;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawSleepyDrug(g2d, x, y);
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
		if (!clicked) {
			if (x <= 400) {
				x += speed;
			} else {
				x += speedSlow;
			}
		} else {
			x += speed;
		}
		if (x < 0)
			destroyed = true;
		if (outOfBound()) {
			hitWithPlayer();
		}
	}

	@Override
	public void hitWithPlayer() {
		// TODO Auto-generated method stub
		MainLogic.setSleep(true);
		destroyed = true;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		speed = -20;
		Player.setScore(Player.getScore() + 350);
		clicked = true;
	}

}
