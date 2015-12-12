package entity;

import Utility.InputUtility;
import Utility.ScreenSize;

public abstract class CollidableEntity extends Entity{


	protected int width,height;
	protected int speed;
	protected int tempspeed;
	protected boolean clicked;
	public CollidableEntity(int x, int y, int z, int speed) {
		super(x, y, z);
		this.speed = speed;
		this.tempspeed = speed;
		// TODOa Auto-generated constructor stub
	}

	public abstract void move();
	public boolean isMouseOver() {
		if(InputUtility.getMouseX()<= x+width) {
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
