package Utility;

import java.applet.Applet;
import java.applet.AudioClip;

import entity.Player;

public class AudioUtility {

	private static AudioClip acBg;
	private static AudioClip acRelax;
	private static AudioClip acBomb;
	private static AudioClip acPause;
	private static AudioClip acAttack;

	static {
		acBg = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/bgMusic01.wav"));
		acRelax = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/RelaxingSong.wav"));
		acBomb = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/bomb.wav"));
		acPause = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/pause.wav"));
		// acAttack =
		// Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/attack.wav"));
	}

	public static void playSound(String identifier) {
		if (identifier.equalsIgnoreCase("bg")) {
			acBg.loop();
		}
		if (identifier.equalsIgnoreCase("relax"))
			acRelax.play();
		if (identifier.equalsIgnoreCase("bomb"))
			acBomb.play();
		if (identifier.equalsIgnoreCase("pause"))
			acPause.play();
		// if(identifier.equalsIgnoreCase("attack"));
		// acAttack.play();
	}
	public static void playMusicBg(boolean isRelax) {
		if(isRelax) {
			acBg.stop();
			acRelax.loop();
		}
		else {
			acBg.loop();
			acRelax.stop();
		}

	}

}
