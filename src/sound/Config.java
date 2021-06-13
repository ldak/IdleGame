package sound;

import java.util.prefs.Preferences;

public class Config {
	private  Preferences pref;
	public Config() {
		// TODO Auto-generated constructor stub
		pref= Preferences.userNodeForPackage(Music.class);
	}
	public boolean isSoundOn() {
		// TODO Auto-generated method stub
		
		return pref.getBoolean("sound", true);
	}
	public void setSoundOff() {
		// TODO Auto-generated method stub
		pref.putBoolean("sound", false);
	}
	public void setSoundOn() {
		// TODO Auto-generated method stub
		pref.putBoolean("sound", true);
	}

}
