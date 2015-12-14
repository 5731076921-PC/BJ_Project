package ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import entity.MainLogic;
import render.GameScreen;
import render.RenderManager;
import utility.AudioUtility;

public class TitleScreen extends JComponent {
	private static BufferedImage title;
	private JComponent gameScreen;
	private JComponent howToPlay;
	private GameWindow gameWindow;

	static {
		try {
			title = ImageIO.read(TitleScreen.class.getClassLoader().getResource("res/img/title.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			title = null;
		}
	}
	public TitleScreen() {
		RenderManager renderManager = new RenderManager();
		MainLogic logic = new MainLogic(renderManager);
		gameScreen = new GameScreen(renderManager);
		gameScreen.setFocusable(true);
		gameWindow = new GameWindow(this);
		Thread x = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized(logic) {
					try {
						logic.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				while(true){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
					gameScreen.repaint();
					logic.logicUpdate();
					}
				}
			}
			
		});

		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getX() >=597 && e.getX()<=964) {
					if(e.getY()>=308 && e.getY()<=388) {
					synchronized(logic) {
					logic.notifyAll();
					gameWindow.switchScene(gameScreen);
					}
					}
					else if(e.getY()>=413 && e.getY()<=494) {
						
					}
					else if(e.getY()>=523 && e.getY()<=601) {
						System.exit(ABORT);
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		x.run();
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(title, null, 0, 0);
	}

}
