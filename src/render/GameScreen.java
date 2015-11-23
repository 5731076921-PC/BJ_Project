package render;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class GameScreen extends JComponent{

	private RenderManager renderManager;
	
	public GameScreen(RenderManager renderManager){
		super();
		setDoubleBuffered(true);
		this.renderManager = renderManager;
		setPreferredSize(new Dimension(640,480));
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		renderManager.drawScreen((Graphics2D)g);
	}
}
