package entity;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Utility.InputUtility;
import Utility.RandomUtility;
import Utility.ScreenSize;
import render.IRenderable;
import render.RenderManager;



public class MainLogic {

	//All renderable objects
	private List<CollidableEntity> onScreenObject = new ArrayList<CollidableEntity>();

	private int zCounter = Integer.MIN_VALUE+1;
	//InitialDelay: ?, Rosen, F, Drug, Cartoon, Music, Bomb
	private int[] nextObjectCreationDelay = {30, 60, 600, 350, 650, 800, 1200};	
	private boolean readyToRender = false; //For dealing with synchronization issue
	private static boolean hitted;
	private static boolean sleep;
	private int hitCounter =0;
	private static int sleepCounter =0;
	private RenderManager renderManager;
	public MainLogic(RenderManager renderManager) {
		this.renderManager = renderManager;
		onStart();
	}
	//Called before enter the game loop
	public synchronized void onStart(){
		hitted = false;
		sleep = false;
	}
	
	//Called after exit the game loop
	public synchronized void onExit(){
		readyToRender = false;
		onScreenObject.clear();
	}
	
	public void logicUpdate(){
		//Paused
		if(InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)){
			Player.setPause(!Player.isPause());
			InputUtility.setKeyTriggered(KeyEvent.VK_ENTER, false);
		}
		
		if(Player.isPause()){
			return;
		}
		if(sleep && sleepCounter<=50) {
			sleepCounter++;
		} else if(sleep) {
			sleep = !sleep;
			sleepCounter= 0;
		}
		
				
		//Create random target
		createTarget();
		
		//Change hitted to false after 5 ticks
		if(hitCounter>0) {
			hitCounter--;
		}
		else { 
			hitCounter=0;
			hitted= false;
		}
		//Attack
		if(InputUtility.isMouseLeftDown() && !sleep) {
			if(getTopEntity() != null) {
				getTopEntity().onClick();
				InputUtility.setMouseLeftDown(false);
			}
			
		}
		//Update target object
		for(CollidableEntity obj : onScreenObject){
			obj.move();
			obj.upSpeed();
			if(obj.outOfBound() && hitCounter ==0) {
				hitted = true;
				hitCounter = 20;
			}
		}
		
		//Remove unused image
		for(int i=onScreenObject.size()-1; i>=0; i--){
			if(onScreenObject.get(i).isDestroyed())
				onScreenObject.remove(i);
		}
	}
	
	private void createTarget(){
//		Help me generate delay and create sleepy drug, cartoon using this constructor.
//		SleepyDrug z = new SleepyDrug(0, 350, zCounter, 20, 3, 0);
//		onScreenObject.add(z);
//		renderManager.add(z);
		
//		Cartoon w = new Cartoon(0, ScreenSize.HEIGHT-146, zCounter, 2);
//		onScreenObject.add(w);
//		renderManager.add(w);

		for (int k = 0; k < nextObjectCreationDelay.length; k++) {
			if (nextObjectCreationDelay[k] > 0) {
				nextObjectCreationDelay[k]--;
			} 
		}	

				// Random next creation delay
				// set nextObjectCreationDelay
		if (nextObjectCreationDelay[0] <= 0) {
			nextObjectCreationDelay[0] = RandomUtility.random(10, 70);
			QuestionMark x = new QuestionMark(0, 450, zCounter, 3);
			onScreenObject.add(x);
			renderManager.add(x);

		}
		if (nextObjectCreationDelay[1] <= 0) {
			nextObjectCreationDelay[1] = RandomUtility.random(40, 90);
			Rosen y = new Rosen(0, RandomUtility.random(100, 400), zCounter, 5);
			onScreenObject.add(y);
			renderManager.add(y);

		}

		if (nextObjectCreationDelay[2] <= 0) {
			nextObjectCreationDelay[2] = RandomUtility.random(500, 850);
			FThrow f[] = new FThrow[5];
			for (int i = 0; i < f.length; i++) {
				f[i] = new FThrow(0, 90 * i, zCounter, 4);
			}
			for (int i = 0; i < f.length; i++) {
				onScreenObject.add(f[i]);
				renderManager.add(f[i]);
			}
		}
		
			//Increase z counter (so the next object will be created on top of the previous one)
			zCounter++;
			if(zCounter == Integer.MAX_VALUE-1){
				zCounter = Integer.MIN_VALUE+1;
			}
		}

	public CollidableEntity getTopEntity() {
		int z =Integer.MIN_VALUE;
		CollidableEntity entity = null;
		for(CollidableEntity e : onScreenObject) {
			if(e.getZ()>z && e.isMouseOver()) {
				z= e.getZ();
				entity = e;
			}
		}
		return entity;
	}
	public static boolean isHitted() {
		return hitted;
	}
	public static boolean isSleep() {
		return sleep;
	}
	public static void setSleep(boolean sleep) {
		MainLogic.sleep = sleep;
	}
	
//	public synchronized List<IRenderable> getSortedRenderableObject() {
//		List<IRenderable> sortedRenderable = new ArrayList<IRenderable>();
//		if(!readyToRender) return sortedRenderable;
//		for(CollidableEntity object : onScreenObject){
//			sortedRenderable.add(object);
//		}
//
//		
//		Collections.sort(sortedRenderable, new Comparator<IRenderable>() {
//			@Override
//			public int compare(IRenderable o1, IRenderable o2) {
//				if(o1.getZ() > o2.getZ())
//					return 1;
//				else if(o1.getZ() < o2.getZ())
//					return -1;
//				else
//					return 0;
//			}
//		});
//		return sortedRenderable;
//	}
}
