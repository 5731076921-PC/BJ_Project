package entity;

import java.util.ArrayList;
import java.util.List;

import render.RenderManager;

public class GameLogic {

	private RenderManager renderableContainer;
	//Note that in this example, we do not share the collection but instead the content (IRenderable) inside the collection
	//as we don't want to have concurrency problem
	private List<Entity> gameObjectContainer;
	
	private Tank tank;
	private Mine mine;
	
	public GameLogic(RenderManager renderableContainer){
		this.renderableContainer = renderableContainer;
		this.gameObjectContainer = new ArrayList<Entity>();
	
		Field field = new Field();
		renderableContainer.add(field);
		tank = new Tank(320,240);
		mine = new Mine(100,100);
		addNewObject(tank);
		addNewObject(mine);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		renderableContainer.add(entity);
	}
	
	public void logicUpdate(){
		tank.update();
		if(!mine.isDestroyed() && tank.collideWith(mine)){
			mine.onCollision(tank);
		}
	}
}
