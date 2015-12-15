package ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
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
import entity.Player;
import render.GameScreen;
import render.RenderManager;
import utility.AudioUtility;
import utility.ScreenSize;

public class GameManager extends JComponent {
	private static BufferedImage title;
	private static BufferedImage tut[] = new BufferedImage[4];
	private static BufferedImage gameOver;
	private JComponent gameScreen;
	private GameWindow gameWindow;
	private RenderManager renderManager;
	private boolean isTutorial;
	private MainLogic logic;
	private int tutorialCounter = 0;
	private boolean showHighScore;
	private Thread x;
	static {
		try {
			title = ImageIO.read(GameManager.class.getClassLoader().getResource("res/img/title.png"));
			tut[0] = ImageIO.read(GameManager.class.getClassLoader().getResource("res/img/tutorial01.png"));
			tut[1] = ImageIO.read(GameManager.class.getClassLoader().getResource("res/img/tutorial02.png"));
			tut[2] = ImageIO.read(GameManager.class.getClassLoader().getResource("res/img/tutorial03.png"));
			tut[3] = ImageIO.read(GameManager.class.getClassLoader().getResource("res/img/tutorial04.png"));
			gameOver = ImageIO.read(GameManager.class.getClassLoader().getResource("res/img/gameOver.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GameManager() {
		this.setPreferredSize(new Dimension(1200, 680));
		renderManager = new RenderManager();
		logic = new MainLogic(renderManager);
		gameScreen = new GameScreen(renderManager);
		gameScreen.setFocusable(true);
		gameWindow = new GameWindow(this);
		x = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (logic) {
					try {
						logic.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					while (!Player.isGameOver()) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						gameScreen.repaint();
						logic.logicUpdate();
					}
					logic.onExit();
				}
				switchToHighScore();
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
				if (isTutorial) {
					if (e.getX() >= 1055 && e.getX() <= 1163) {
						if (e.getY() >= 557 && e.getY() <= 656) {
							tutorialCounter++;
							if (tutorialCounter == 4) {
								tutorialCounter = 0;
								isTutorial = false;
							}
							repaint();
						}
					}
				} else if(showHighScore) {
					if (e.getX() >= 1055 && e.getX() <= 1163) {
						if (e.getY() >= 557 && e.getY() <= 656) {
							showHighScore = false;
							}
							repaint();
						}
				}
				else if (e.getX() >= 597 && e.getX() <= 964) {
					if (e.getY() >= 308 && e.getY() <= 388) {
						synchronized (logic) {
							logic.notifyAll();
							gameWindow.switchScene(gameScreen);

						}
					} else if (e.getY() >= 413 && e.getY() <= 494) {
						isTutorial = true;
						repaint();
					} else if (e.getY() >= 523 && e.getY() <= 601) {
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
		x.start();
	}
	public void switchToHighScore() {
		showHighScore = true;
		gameWindow.switchScene(this);
		x = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					synchronized (logic) {
						try {
							logic.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						while (!Player.isGameOver()) {
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {
							}
							gameScreen.repaint();
							logic.logicUpdate();
						}
						logic.onExit();
					}
					switchToHighScore();
				}

			});
		 x.start();

	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (isTutorial) {
			g2.drawImage(tut[tutorialCounter], null, 0, 0);
		} else if(showHighScore) {
			g2.drawImage(gameOver, null, 0, 0);
			g2.setFont(new Font("Tahoma", Font.BOLD, 60));
			int w = (int)g2.getFontMetrics().getStringBounds(""+Player.getTempScore(), g2).getWidth()/2;
			g2.drawString(""+Player.getTempScore(), ScreenSize.WIDTH/2 -w, ScreenSize.HEIGHT/2+60);
		} else
			g2.drawImage(title, null, 0, 0);
	}

}
