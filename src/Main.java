import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import entity.GameLogic;
import render.GameScreen;
import render.RenderManager;


public class Main {

	public static void main(String[] args){
		JFrame frame = new JFrame("Let's take a break!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RenderManager renderManager = new RenderManager();
		//GameLogic logic = new GameLogic(renderManager);
		JComponent gameScreen = new GameScreen(renderManager);
		frame.getContentPane().add(gameScreen);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(new Dimension(1200, 680));
		frame.setResizable(false);
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			gameScreen.repaint();
			//logic.logicUpdate();
		}
	}
}
