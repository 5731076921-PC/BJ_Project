package entity;

public abstract class CollidableEntity extends Entity{
	protected int radius;
	protected int speed;
	
	protected boolean collideWith(CollidableEntity other){
		return Math.hypot(this.x-other.x, this.y-other.y) <= this.radius+other.radius;
	}
	public void hitWithOther(Helper e){
		if(this instanceof Enemy && this.collideWith(e)) {
			e.destroyed = false;
		}
	}
	public void move() {
		
	}
	
}
