import javax.swing.JComponent;
import javax.swing.JFrame;

import entity.MainLogic;
import render.GameScreen;
import render.RenderManager;
import ui.TitleScreen;

public class Main {

	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		RenderManager renderManager = new RenderManager();
// 		MainLogic logic = new MainLogic(renderManager);
// 		JComponent gameScreen = new GameScreen(renderManager);
//		gameScreen.setFocusable(true);
// 		frame.getContentPane().add(gameScreen);
// 		frame.pack();
// 		frame.setVisible(true);
// 		while(true) {
// 			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
// 			logic.logicUpdate();
// 			frame.repaint();
// 		}
		TitleScreen t = new TitleScreen();
	}
}
