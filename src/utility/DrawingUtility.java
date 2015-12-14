package utility;

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
	protected static final Font smallFont = new Font("Tahoma", Font.BOLD, 20);
	private static final int STRESSBAR_WIDTH = 65;
	private static final int STRESSBAR_HEIGHT = 390;
	private static final int STRESSBAR_X = 1110;
	private static final int STRESSBAR_Y = 180;
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
	protected static final BufferedImage bg = getImage("res/img/bg.png");
	protected static final BufferedImage questionMarkL = getImage("res/img/questionMark-L.png");
	protected static final BufferedImage questionMarkR = getImage("res/img/questionMark-R.png");
	protected static final BufferedImage student = getImage("res/img/player.png");
	protected static final BufferedImage studentHit = getImage("res/img/player-hit.png");
	protected static final BufferedImage student2 = getImage("res/img/player-2.png");
	protected static final BufferedImage student3 = getImage("res/img/player-3.png");
	protected static final BufferedImage rosen = getImage("res/img/rosen.png");
	protected static final BufferedImage f = getImage("res/img/f.png");
	protected static final BufferedImage sleepyDrug = getImage("res/img/drug.png");
	protected static final BufferedImage sleepyDrugBG = getImage("res/img/sleepingBG.png");
	protected static final BufferedImage comic = getImage("res/img/comic.png");
	protected static final BufferedImage music = getImage("res/img/music.png");
	protected static final BufferedImage bomb = getImage("res/img/bomb.png");
	
	public static void drawBackground(Graphics2D g2,boolean sleep) {
		g2.clearRect(0, 0, 1200,680);
		if(!sleep)
			g2.drawImage(bg, null, 0, 0);
		else
			g2.drawImage(sleepyDrugBG, null, 0, 0);

	}
	public static void drawPauseString(Graphics2D g2) {
		if(Player.isPause()) {
			int h= (int) g2.getFontMetrics().getStringBounds("PAUSED", g2).getHeight();
			int w= (int) g2.getFontMetrics().getStringBounds("PAUSED", g2).getWidth();
			g2.setFont(standardFont);
			g2.setColor(Color.RED);
			g2.drawString("PAUSED", ScreenSize.WIDTH/2-w, ScreenSize.HEIGHT/2-h);
		}
	}
	public static void drawScoreBar(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(standardFont);
		int h= (int) g2.getFontMetrics().getStringBounds("SCORE", g2).getHeight();
		g2.drawString("SCORE: "+ Player.getScore()  , ScreenSize.WIDTH*7/9, h);
	}
	public static void drawQuestionMarkItem(Graphics2D g2,int x,int direction) {
		BufferedImage questionMark;
		if(direction ==1) questionMark =  questionMarkL;
		else questionMark = questionMarkR;
	g2.drawImage(questionMark, null, x, 450);
	}
	public static void drawStudent(Graphics2D g2,int level,boolean playerIsHit) {
		if(playerIsHit) {
			g2.drawImage(studentHit, null, ScreenSize.STUDENTBOUND, 150);
			return;
		}
		else if(level ==1) {
			g2.drawImage(student, null, ScreenSize.STUDENTBOUND, 150);
		}
		else if(level==2) {
			g2.drawImage(student2, null, ScreenSize.STUDENTBOUND, 150);
		}
		else {
			g2.drawImage(student3, null, ScreenSize.STUDENTBOUND, 150);
		}
	}
	public static void drawStressBar(Graphics2D g2,int stressLevel) {
		g2.setColor(Color.WHITE);
		g2.fillRect(STRESSBAR_X, STRESSBAR_Y, STRESSBAR_WIDTH,STRESSBAR_HEIGHT);
		g2.setColor(new Color(223,15, 54));
		int level = stressLevel*STRESSBAR_HEIGHT/100;
		g2.fillRect(STRESSBAR_X, STRESSBAR_Y+STRESSBAR_HEIGHT-level, STRESSBAR_WIDTH, level);

		g2.setFont(smallFont);
		g2.setColor(Color.BLACK);
		g2.drawString("Stress", STRESSBAR_X+4, ScreenSize.HEIGHT-90);
		g2.drawString("Level", STRESSBAR_X+7, ScreenSize.HEIGHT-70);
	}
	public static void drawRosen(Graphics2D g2,int x, int y) {
		g2.drawImage(rosen, null, x, y);
	}
	public static void drawF(Graphics2D g2,int x, int y) {
		g2.drawImage(f, null, x, y);
	}
	public static void drawSleepyDrug(Graphics2D g2,int x, int y){
		g2.drawImage(sleepyDrug, null, x, y);
	}
	
	public static void drawSleepyDrugBG(Graphics2D g2,int x, int y){
		g2.drawImage(sleepyDrugBG, null, x, y);
	}
	public static void drawComic(Graphics2D g2,int x, int y) {
		g2.drawImage(comic, null, x, y);
	}
	public static void drawMusic(Graphics2D g2,int x, int y) {
		g2.drawImage(music, null, x, y);
	}
	public static void drawBomb(Graphics2D g2,int x, int y) {
		g2.drawImage(bomb, null, x, y);
	}
	
	

}