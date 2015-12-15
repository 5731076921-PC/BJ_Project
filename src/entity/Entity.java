package entity;

import exception.GameOverException;
import render.IRenderable;
import utility.InputUtility;
import utility.ScreenSize;

public abstract class Entity implements IRenderable {

	protected int x,y,z;          
	protected boolean visible,destroyed;
	protected int width,height;
	protected int speed;
	protected int tempspeed;
	protected boolean clicked;
	public Entity(int x, int y, int z, int speed) {
		this.x=x;
		this.y=y;
		this.z = z;
		this.visible = true;
		this.destroyed = false;
		this.speed = speed;
		this.tempspeed = speed;
	}
	public abstract void hitWithPlayer();
	public abstract void onClick();
	public abstract void move();
	public boolean isMouseOver() {
		if(InputUtility.getMouseX()<= x+width && InputUtility.getMouseX() >=x) {
			if(InputUtility.getMouseY()>y && InputUtility.getMouseY()<y+height) {
				return true;
			}
		}
		return false;
	}
	 public void upSpeed() {
		 if(!clicked)
		 this.speed = tempspeed*Player.getLevel();
		 if(MainLogic.isRelax() && !clicked) this.speed = 2;
	 }
	 public boolean outOfBound() {
		 return x+width>= ScreenSize.STUDENTBOUND;
	 }
}
