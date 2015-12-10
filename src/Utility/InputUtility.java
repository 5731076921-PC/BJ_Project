package Utility;

public class InputUtility {

	public static int mouseX,mouseY;
	public static boolean mouseOnScreen = true;
	private static boolean isLeftDown = false;
	private static boolean isLeftClickedLastTick = false;
	private static boolean keyPressed[] = new boolean[256];
	
	public static boolean getKeyPressed(int key) {
		if(key < 0 || key >= 256) return false;
		return keyPressed[key];
	}
	public static void setKeyPressed(int key,boolean pressed) {
		if(key < 0 || key >= 256) return;
		InputUtility.keyPressed[key] = pressed;
	}
	
	public static void mouseLeftDown(){
		isLeftDown = true;
		isLeftClickedLastTick = true;
	}
	
	public static void mouseLeftRelease(){
		isLeftDown = false;
	}
	
	public static boolean isLeftClickTriggered(){
		return isLeftClickedLastTick;
	}
	
	public static void postUpdate(){
		isLeftClickedLastTick = false;
	}
	
}
