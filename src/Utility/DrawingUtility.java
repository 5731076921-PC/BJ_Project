package Utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;


public class DrawingUtility {
	protected static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	protected static final Font smallFont = new Font("Tahoma", Font.PLAIN, 9);
	private static BufferedImage getImage(String directory){
		BufferedImage img = null;
		try {
			ClassLoader x = DrawingUtility.class.getClassLoader();
			img = ImageIO.read(x.getResource(directory));
		} catch(IOException e) {
			img = null;
		}
		return img;
	}
	protected static final BufferedImage bg = getImage("res/img/bg.jpg");
	protected static final BufferedImage questionMark = getImage("res/img/questionMark.jpg");
	protected static final BufferedImage rosen = getImage("res/img/rosen.jpg");
	protected static final BufferedImage sleepyDrug = getImage("res/img/bg.jpg");

	public static void drawBackground(Graphics2D g2) {
		g2.clearRect(0, 0, ScreenSize.WIDTH, ScreenSize.HEIGHT);
		g2.drawImage(bg, null, 0, 0);
	}
	public static void drawScoreBar(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(standardFont);
		int h= (int) g2.getFontMetrics().getStringBounds("SCORE", g2).getHeight();
		g2.drawString("SCORE: " + Player.getScore() , ScreenSize.WIDTH*8/10, h);
	}
	public static void drawQuestionMarkItem(Graphics2D g2,BufferedImage img, int x,int y,int direction) {
	AffineTransform at = new AffineTransform();
	int radius = img.getWidth()/2;
	g2.drawImage(img, null, x, y);
	}
	

}
