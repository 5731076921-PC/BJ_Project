package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import entity.MainLogic;
import entity.Player;
import utility.DrawingUtility;

public class RenderManager {
	
	private CopyOnWriteArrayList<IRenderable> entities;
	
	public RenderManager(){
		entities = new CopyOnWriteArrayList<IRenderable>();
	}

	public void add(IRenderable entity){
		entities.add(entity);
		//Sort our list by Z -- we don't sort during "draw call" as it's not efficient
		Collections.sort(entities, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				if(o1.getZ() > o2.getZ()) return 1;
				return -1;
			}
		});
	}
	
	//Use to remove destroyed object(s) which won't be drawn again
	public void update(){
		
		for(int i=entities.size()-1; i>=0; i--){
			if(entities.get(i).isDestroyed())
				entities.remove(i);
		}
		
	}
	public void clear() {
		entities.clear();
	}
	//Will be called by JComponent object
	public synchronized void drawScreen(Graphics2D g2d){
		DrawingUtility.drawBackground(g2d,MainLogic.isSleep());
		DrawingUtility.drawScoreBar(g2d);
		DrawingUtility.drawStudent(g2d,Player.getLevel(),MainLogic.isHitted());
		DrawingUtility.drawStressBar(g2d, Player.getStressLevel());
		for(IRenderable entity : entities){
			if(entity.isVisible() && !entity.isDestroyed()){
				entity.draw(g2d);
			}
		}
		if(Player.isPause()) {
		DrawingUtility.drawPauseString(g2d);
		}
		this.update();
	}
}
