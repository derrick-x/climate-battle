import java.awt.Color;
import java.awt.Graphics;

public class Message {
	String text;
	int time;
	public Message(String txt, int t) {
		text = txt;
		time = t;
	}
	public void tick() {
		time--;
		if (time<=0) {
			Main.removeMessage(this);
		}
	}
	public Graphics drawMessage(Graphics g, int y) {
		g.setColor(Color.gray);
		g.fillRect(30, y, 600, 20);
		g.setColor(Color.white);
		g.drawString(text, 40, y+14);
		return g;
	}
}
