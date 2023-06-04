
public class Splash {
	int xPos;
	int yPos;
	int timer;
	public Splash(double x, double y) {
		xPos = (int)x;
		yPos = (int)y;
		timer = 50;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public int getTimer() {
		return timer;
	}
	public void tick() {
		timer--;
		if (timer==0) {
			Main.despawnSplash(this);
		}
	}
}
