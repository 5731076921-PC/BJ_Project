package entity;

import Utility.InputUtility;

public abstract class CollidableEntity extends Entity{


	protected int width,height;
	protected int speed;
	protected int tempspeed;
	
	public CollidableEntity(int x, int y, int z, int speed) {
		super(x, y, z);
		this.speed = speed;
		this.tempspeed = speed;
		// TODOa Auto-generated constructor stub
	}
	
//	protected boolean collideWith(CollidableEntity other){
//		return Math.hypot(this.x-other.x, this.y-other.y) <= this.radius+other.radius;
//	}
//	public void hitWithOther(Helper e){
//		if(this instanceof Enemy && this.collideWith(e)) {
//			e.destroyed = false;
//		}
//	}
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
		 this.speed = tempspeed*Player.getLevel();
	 }
}
