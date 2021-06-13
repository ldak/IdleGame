package sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;
import java.util.prefs.Preferences;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class Music {
	private Clip music;
	private static Config pref=new Config();
	
	public Music() {

		try {
			music = AudioSystem.getClip();
			InputStream src = Music.class.getResourceAsStream("BlackFly.wav");
			AudioInputStream stream = AudioSystem.getAudioInputStream(new BufferedInputStream(src));
			music.open(stream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		if (isOn()) {
			music.start();
			music.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	
	public static boolean isOn() {

		return pref.isSoundOn();
	}

	public static void setSoundOff() {
		pref.setSoundOff();
	}
	
	public static void setSoundOn(){
		pref.setSoundOn();

	}
	
}
