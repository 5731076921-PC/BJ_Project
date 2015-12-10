package Utility;

import java.applet.Applet;
import java.applet.AudioClip;

public class AudioUtility {
		
	private static AudioClip acShoot;
	private static AudioClip acCollect;
	static{
		acShoot = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/se/shoot.wav"));
		acCollect = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/se/collect.wav"));
	}
	
	public static void playSound(String identifier){
		if(identifier.equalsIgnoreCase("shoot"))
			acShoot.play();
		if(identifier.equalsIgnoreCase("collect"))
			acCollect.play();
	}
}
