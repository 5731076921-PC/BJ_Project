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

import javax.imageio.ImageIO;

public class RenderManager {

	public static BufferedImage mapSprite;
	public static BufferedImage mineSprite;
//	private static BufferedImage getImage(String directory){
//		BufferedImage img = null;
//		try {
//			ClassLoader x = RenderManager.class.getClassLoader();
//			img = ImageIO.read(x.getResource(directory));
//		} catch(IOException e) {
//			img = null;
//		}
//		return img;
//	}
	static{
		try {
//			mapSprite = ImageIO.read(new File("Map.png"));
//			mineSprite = ImageIO.read(new File("Mine.png"));
			
			ClassLoader loader = RenderManager.class.getClassLoader();
			mapSprite = ImageIO.read(loader.getResource("Map.png"));
			mineSprite = ImageIO.read(loader.getResource("Mine.png"));
			
		} catch (IOException e) {
			mapSprite = null;
			mineSprite = null;
		}
	}
	
	
	private List<IRenderable> entities;
	
	public RenderManager(){
		entities = new ArrayList<IRenderable>();
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
	
	//Will be called by JComponent object
	public void drawScreen(Graphics2D g2d){
		g2d.setBackground(Color.BLACK);
		g2d.clearRect(0, 0, 640, 480);
		for(IRenderable entity : entities){
			if(entity.isVisible() && !entity.isDestroyed()){
				entity.draw(g2d);
			}
		}
	}
}
