package entity;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import render.IRenderable;
import render.RenderManager;
import utility.AudioUtility;
import utility.InputUtility;
import utility.RandomUtility;
import utility.ScreenSize;

public class MainLogic {

	// All renderable objects
	private List<Entity> onScreenObject = new ArrayList<Entity>();

	private int zCounter = Integer.MIN_VALUE + 1;
	// InitialDelay: ?, Rosen, F, Drug, Cartoon, Music, Bomb
	private int[] nextObjectCreationDelay = { 30, 60, 600, 350, 650, 850, 1200 };
	// BACKUP
	// private int[] nextObjectCreationDelay = {30, 60, 600, 350, 650,850,1200};
	private static boolean hitted;
	private static boolean sleep;
	private static boolean bomb;
	private static boolean relax;
	public static boolean gameOverFlag;
	private int hitCounter = 0;
	private int sleepCounter = 0;
	private int relaxCounter = 0;
	private RenderManager renderManager;

	public MainLogic(RenderManager renderManager) {
		this.renderManager = renderManager;
		onStart();
	}

	// Called before enter the game loop
	public synchronized void onStart() {
		hitted = false;
		sleep = false;
		AudioUtility.playSound("bg");
	}

	// Called after exit the game loop
	public synchronized void onExit() {
		onScreenObject.clear();
		renderManager.clear();
		hitCounter =0;
		sleepCounter =0;
		relaxCounter =0;
		hitted = false;
		sleep =false;
		bomb =false;
		relax = false;
		Player.clear();
	}

	public void logicUpdate() {
		// Paused
		synchronized(renderManager) {
		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
			Player.setPause(!Player.isPause());
			InputUtility.setKeyTriggered(KeyEvent.VK_ENTER, false);
		}
		if(!Player.isPause())
			renderManager.notifyAll();
		}

		if (Player.isPause()) {
			return;
		}
		if (sleep && sleepCounter <= 150) {
			sleepCounter++;
		} else if (sleep) {
			sleep = !sleep;
			sleepCounter = 0;
		}
		if (relax && relaxCounter == 0) {
			AudioUtility.playMusicBg(relax);
		}
		if (relax && relaxCounter <= 1400) {
			relaxCounter++;
		} else if (relax) {
			relax = !relax;
			relaxCounter = 0;
			AudioUtility.playMusicBg(relax);
		}

		// Create random target
		createTarget();

		// Change hitted to false after 5 ticks
		if (hitCounter > 0) {
			hitCounter--;
		} else {
			hitCounter = 0;
			hitted = false;
		}
		// Attack
		if (InputUtility.isMouseLeftDown() && !sleep) {
			if (getTopEntity() != null) {
				getTopEntity().onClick();
				InputUtility.setMouseLeftDown(false);
			}

		}

		// Update target object
		synchronized (renderManager) {
			for (Entity obj : onScreenObject) {
				if (isBomb())
					obj.destroyed = true;
				obj.move();
				obj.upSpeed();
				if (obj.outOfBound() && hitCounter == 0) {
					hitted = true;
					hitCounter = 20;
				}
			}
			// setBomb false after destroyed all object
			setBomb(false);

			// Remove unused image
			for (int i = onScreenObject.size() - 1; i >= 0; i--) {
				if (onScreenObject.get(i).isDestroyed())
					onScreenObject.remove(i);
			}
			renderManager.notify();
		}
	}

	private void createTarget() {

		for (int k = 0; k < nextObjectCreationDelay.length; k++) {
			if (nextObjectCreationDelay[k] > 0) {
				nextObjectCreationDelay[k]--;
			}
		}
		// Random next creation delay
		// set nextObjectCreationDelay
		if (nextObjectCreationDelay[0] <= 0) {
			nextObjectCreationDelay[0] = RandomUtility.random(10, 70);
			if(relax) nextObjectCreationDelay[0] *=2;
			QuestionMark x = new QuestionMark(0, 450, zCounter, 3);
			onScreenObject.add(x);
			renderManager.add(x);

		}
		if (nextObjectCreationDelay[1] <= 0) {
			nextObjectCreationDelay[1] = RandomUtility.random(50, 90);
			if(relax) nextObjectCreationDelay[0] *=2;
			Rosen y = new Rosen(0, RandomUtility.random(100, 400), zCounter, 5);
			onScreenObject.add(y);
			renderManager.add(y);

		}

		if (nextObjectCreationDelay[2] <= 0) {
			nextObjectCreationDelay[2] = RandomUtility.random(500, 850);
			if(relax) nextObjectCreationDelay[0] *=2;
			FThrow f[] = new FThrow[5];
			for (int i = 0; i < f.length; i++) {
				f[i] = new FThrow(0, 90 * i, zCounter, 4);
			}
			for (int i = 0; i < f.length; i++) {
				onScreenObject.add(f[i]);
				renderManager.add(f[i]);
			}
		}

		if (nextObjectCreationDelay[3] <= 0) {
			nextObjectCreationDelay[3] = RandomUtility.random(500, 700);
			if(relax) nextObjectCreationDelay[0] *=2;
			SleepyDrug z = new SleepyDrug(0, 350, zCounter, 20, 3);
			onScreenObject.add(z);
			renderManager.add(z);
		}

		if (nextObjectCreationDelay[4] <= 0) {
			nextObjectCreationDelay[4] = RandomUtility.random(400, 550);
			if(relax) nextObjectCreationDelay[0] *=2;
			Cartoon w = new Cartoon(0, ScreenSize.HEIGHT - 146, zCounter, 2);
			onScreenObject.add(w);
			renderManager.add(w);
		}

		if (nextObjectCreationDelay[5] <= 0) {
			nextObjectCreationDelay[5] = RandomUtility.random(650, 800);
			if(relax) nextObjectCreationDelay[0] *=2;
			Music m = new Music(0, ScreenSize.HEIGHT - 146, zCounter, 2);
			onScreenObject.add(m);
			renderManager.add(m);
		}

		if (nextObjectCreationDelay[6] <= 0) {
			nextObjectCreationDelay[6] = RandomUtility.random(800, 1100);
			if(relax) nextObjectCreationDelay[0] *=2;
			Bomb b = new Bomb(0, ScreenSize.HEIGHT - 146, zCounter, 2);
			onScreenObject.add(b);
			renderManager.add(b);
		}

		// Increase z counter (so the next object will be created on top of the
		// previous one)
		zCounter++;
		if (zCounter == Integer.MAX_VALUE - 1) {
			zCounter = Integer.MIN_VALUE + 1;
		}
	}

	public Entity getTopEntity() {
		int z = Integer.MIN_VALUE;
		Entity entity = null;
		for (Entity e : onScreenObject) {
			if (e.getZ() > z && e.isMouseOver()) {
				z = e.getZ();
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

	public static boolean isBomb() {
		return bomb;
	}

	public static void setBomb(boolean bomb) {
		MainLogic.bomb = bomb;
	}

	public static boolean isRelax() {
		return relax;
	}

	public static void setRelax(boolean relax) {
		MainLogic.relax = relax;
	}



	// public synchronized List<IRenderable> getSortedRenderableObject() {
	// List<IRenderable> sortedRenderable = new ArrayList<IRenderable>();
	// if(!readyToRender) return sortedRenderable;
	// for(CollidableEntity object : onScreenObject){
	// sortedRenderable.add(object);
	// }
	//
	//
	// Collections.sort(sortedRenderable, new Comparator<IRenderable>() {
	// @Override
	// public int compare(IRenderable o1, IRenderable o2) {
	// if(o1.getZ() > o2.getZ())
	// return 1;
	// else if(o1.getZ() < o2.getZ())
	// return -1;
	// else
	// return 0;
	// }
	// });
	// return sortedRenderable;
	// }
}
