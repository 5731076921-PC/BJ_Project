package ui;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{

	private JComponent currentScene;
	
	public GameWindow(JComponent scene){
		super("Let's Take a Break!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.currentScene = scene;
		getContentPane().add(currentScene);
		this.pack();
		setVisible(true);
		currentScene.requestFocus();
	}
	
	public void switchScene(JComponent scene){
		getContentPane().remove(currentScene);
		this.currentScene = scene;
		getContentPane().add(currentScene);
		getContentPane().validate();
		pack();
		currentScene.requestFocus();
	}
	
	public JComponent getCurrentScene(){
		return currentScene;
	}
}
