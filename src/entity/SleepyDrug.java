package entity;

import java.awt.Graphics2D;

import Utility.DrawingUtility;
import Utility.ScreenSize;

public class SleepyDrug extends Enemy {
private int speedSlow;
private boolean hit;
private int sleepyTime;
private int sleepyTimeCount;

	public SleepyDrug(int x, int y, int z, int speed, int speedSlow,int sleepyTime) {
		super(x, y, z, speed);
		// TODO Auto-generated constructor stub
		this.speedSlow = speedSlow;
		this.hit = false;
		this.sleepyTime = sleepyTime;
		this.sleepyTimeCount = 0;
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
		
		//DrawingEffect
//		if(hit == true) {
//			DrawingUtility.drawSleepyDrugBG(g2d, 0, 0);
//		}
//		else {
//			DrawingUtility.drawSleepyDrug(g2d, x, y);
//		}
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
		if(x <= ScreenSize.WIDTH/2) {
			x+= speed;
		} else {
			x+= speedSlow;
		}
		if(x<0) destroyed = true;
		if(this.sleepyTimeCount == sleepyTime){
			hit = false;
			sleepyTimeCount = 0;
		}
		sleepyTimeCount++;
	}

	@Override
	public void hitWithPlayer() {
		// TODO Auto-generated method stub
		this. hit = true;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		speed = -20;
		Player.setScore(Player.getScore()+350);
	}
	
	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public int getSleepyTime() {
		return sleepyTime;
	}

	public void setSleepyTime(int sleepyTime) {
		this.sleepyTime = sleepyTime;
	}

	public int getSleepyTimeCount() {
		return sleepyTimeCount;
	}

	public void setSleepyTimeCount(int sleepyTimeCount) {
		this.sleepyTimeCount = sleepyTimeCount;
	}

}
