package entity;

import java.awt.Graphics2D;

public class QuestionMark extends Enemy {	
	
	public QuestionMark(int x, int y, int z, int speed) {
		super(x, y, z, speed);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
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
		// TODO Auto-generated method stub
		x += speed;
		if(x<0) destroyed = true;
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
		speed = -50;
		Player.setScore(Player.getScore()+150);
	}


}
