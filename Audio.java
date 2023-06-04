//Sounds are from zapsplat.com

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Audio {
	static String nowPlaying = "";
	static long startTime = 0;
	static Clip clip;
	static Clip alarm;
	public static void playPop() {
		File file = new File("Pop.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static void playHit() {
		File file = new File("hit.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static void playLose() {
		clip.stop();
		if (alarm!=null) {
			alarm.stop();
		}
		File file = new File("lose.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static void playWin() {
		clip.stop();
		if (alarm!=null) {
			alarm.stop();
		}
		File file = new File("win.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static void playExplode() {
		File file = new File("explode.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static void playDamage() {
		File file = new File("damage.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static void playAlarm() {
		if (alarm==null||!alarm.isActive()) {
			File file = new File("alarm.wav");
			try {
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				alarm = AudioSystem.getClip();
				alarm.open(audioStream);
				alarm.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	public static void playWind() {
		File file = new File("wind.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void playBuzz() {
		File file = new File("buzz.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void playWater() {
		File file = new File("water.wav");
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void music() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
		if (Main.gameStage<2) {
			if (nowPlaying!="menu") {
				nowPlaying = "menu";
				File file = new File("menu.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				try {
					clip.stop();
				} catch (NullPointerException e) {
					
				}
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.start();
				startTime = System.currentTimeMillis();
			} else if (System.currentTimeMillis()>startTime+263000) {
				File file = new File("menu.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				clip.stop();
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.start();
				startTime = System.currentTimeMillis();
			}
		} else if (Main.gameStage==2) {
			if (Main.difficulty==2) {
				if (nowPlaying!="hard") {
					nowPlaying = "hard";
					File file = new File("hard.wav");
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
					clip.stop();
					clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
					startTime = System.currentTimeMillis();
				} else if (System.currentTimeMillis()>startTime+93000) {
					File file = new File("hard.wav");
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
					clip.stop();
					clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
					startTime = System.currentTimeMillis();
				}
			} else {
				if (nowPlaying!="ingame") {
					nowPlaying = "ingame";
					File file = new File("ingame.wav");
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
					clip.stop();
					clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
					startTime = System.currentTimeMillis();
				} else if (System.currentTimeMillis()>startTime+140000) {
					File file = new File("ingame.wav");
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
					clip.stop();
					clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
					startTime = System.currentTimeMillis();
				}
			}
		}
	}
}
