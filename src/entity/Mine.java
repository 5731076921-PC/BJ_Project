package entity;

import java.awt.Graphics2D;

import render.RenderManager;

public class Mine extends CollidableEntity{

	public Mine(int x,int y){
		this.x = x;
		this.y = y;
		this.z = -100;
		this.radius = 20;
	}
	
	public void onCollision(Tank tank){
		tank.hitByMine();
		this.destroyed = true;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(RenderManager.mineSprite, null, x-radius, y-radius);
	}

}
