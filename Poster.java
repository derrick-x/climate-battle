import java.awt.Color;
import java.awt.Graphics;

public class Poster {
	int xPos;
	int yPos;
	int moneyStored;
	public Poster(int x, int y) {
		xPos = x;
		yPos = y;
		moneyStored = 0;
	}
	public Poster(double x, double y) {
		xPos = (int)x;
		yPos = (int)y;
		moneyStored = 0;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public int getMoney() {
		return moneyStored;
	}
	public void collectMoney() {
		if (moneyStored>0) {
			Shop.changeMoney(moneyStored);
			moneyStored = 0;
		}
	}
	public void tick() {
		if (Math.random()*1000<2) {
			moneyStored+=5;
			if (Math.random()*10<1) {
				moneyStored+=15;
			}
		}
	}
	public Graphics drawPoster(Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(x, y, 60, 45);
		g.setColor(Color.orange);
		g.drawString("   Fight", x, y+13);
		g.drawString(" Climate", x, y+26);
		g.drawString(" Change", x, y+39);
		g.setColor(Color.black);
		g.drawString("$"+moneyStored, x, y+55);
		return g;
	}
	public static Graphics firstPoster(Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(x, y, 60, 45);
		g.setColor(Color.orange);
		g.drawString("   Fight", x, y+13);
		g.drawString(" Climate", x, y+26);
		g.drawString(" Change", x, y+39);
		return g;
	}
}
