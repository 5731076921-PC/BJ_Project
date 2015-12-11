package entity;

import render.IRenderable;

public abstract class Entity implements IRenderable{
	protected int x,y,z;          
	protected boolean visible,destroyed;
	
	public Entity(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.visible = true;
		this.destroyed = false;
	}
	
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	public abstract void hitWithPlayer();
	public abstract void onClick();
	

}
