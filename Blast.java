import java.awt.Color;
import java.awt.Graphics;

public class Blast {
	int xPos;
	int yPos;
	int explodeTick;
	public Blast(double x, double y) {
		xPos = (int)x;
		yPos = (int)y;
		explodeTick = 120;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public void tick() {
		explodeTick--;
		if (explodeTick<=0) {
			for (int i=0; i<40; i++) {
				Main.lasers.add(new Laser(Math.toRadians(i*9), xPos, yPos, 50));
			}
			Main.blast(this);
		}
	}
	public static Graphics placeBlast(Graphics g, int x, int y) {
		g.setColor(Color.black);
		g.fillOval(x-10, y-10, 20, 20);
		g.setColor(Color.green);
		g.drawString("3", x-3, y+3);
		return g;
	}
	public Graphics drawBlast(Graphics g, int x, int y) {
		if (explodeTick%40<20) {
			g.setColor(Color.black);
		} else {
			g.setColor(Color.white);
		}
		g.fillOval(x-10, y-10, 20, 20);
		g.setColor(Color.green);
		g.drawString((int)(Math.ceil(explodeTick/40.0))+"", x-3, y+3);
		return g;
	}
}
