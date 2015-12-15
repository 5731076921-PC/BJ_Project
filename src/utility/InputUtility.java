package utility;


public class InputUtility {

	private static int mouseX,mouseY;
	private static boolean mouseLeftDown;
	private static boolean mouseLeftTriggered;
	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyTriggered = new boolean[256];
	
	public static int getMouseX() {
		return mouseX;
	}
	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}
	public static int getMouseY() {
		return mouseY;
	}
	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
	}
	
	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}
	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}
	public static boolean isMouseLeftClicked() {
		return mouseLeftTriggered;
	}
	public static void setMouseLeftTriggered(boolean v){ 
		InputUtility.mouseLeftTriggered = v;
	}
	public static boolean getKeyPressed(int key) {
		if(key>255 || key<0) return false;
		return keyPressed[key];
	}
	public static void setKeyPressed(int key,boolean pressed) {
		if(key>255 || key<0) return;
		InputUtility.keyPressed[key] = pressed;
	}
	
	public static boolean getKeyTriggered(int key) {
		if(key>255 || key<0) return false;
		return keyTriggered[key];
	}
	public static void setKeyTriggered(int key,boolean pressed) {
		if(key>255 || key<0) return;
		InputUtility.keyTriggered[key] = pressed;
	}
	
	public static void postUpdate(){
		setMouseLeftTriggered(false);
		for (int key = 0; key < 256; key++) {
			setKeyTriggered(key, false);
		}
	}
}
