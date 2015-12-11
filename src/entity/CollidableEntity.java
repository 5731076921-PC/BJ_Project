package entity;

public abstract class CollidableEntity extends Entity{


	protected int radius;
	protected int speed;
	public CollidableEntity(int x, int y, int z, int speed) {
		super(x, y, z);
		this.speed = speed;
		// TODOa Auto-generated constructor stub
	}
	
	protected boolean collideWith(CollidableEntity other){
		return Math.hypot(this.x-other.x, this.y-other.y) <= this.radius+other.radius;
	}
	public void hitWithOther(Helper e){
		if(this instanceof Enemy && this.collideWith(e)) {
			e.destroyed = false;
		}
	}
	public abstract void move();
	public abstract boolean isMouseOver();
	 public void upSpeed() {
		 this.speed = speed*Player.getLevel();
	 }
}
