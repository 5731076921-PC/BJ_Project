package utility;

import java.applet.Applet;
import java.applet.AudioClip;

import entity.Player;

public class AudioUtility {
//Sound
	private static AudioClip acBg;
	private static AudioClip acRelax;
	private static AudioClip acBomb;
	private static AudioClip acHit;

	static {
		acBg = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/bgMusic01.wav"));
		acRelax = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/RelaxingSong.wav"));
		acBomb = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/bomb.wav"));
		acHit = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/sound/hit.wav"));
	}

	public static void playSound(String identifier) {
		if (identifier.equalsIgnoreCase("bg")) {
			acBg.loop();
		}
		if (identifier.equalsIgnoreCase("relax"))
			acRelax.play();
		if (identifier.equalsIgnoreCase("bomb"))
			acBomb.play();
		if (identifier.equalsIgnoreCase("hit"))
			acHit.play();
	}

	public static void playMusicBg(boolean isRelax) {
		if (isRelax) {
			acBg.stop();
			acRelax.loop();
		} else {
			acBg.loop();
			acRelax.stop();
		}

	}

}
