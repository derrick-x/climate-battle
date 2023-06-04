import java.awt.Graphics;

public class Flower {
	int xPos;
	int yPos;
	int shootTick;
	int pollinateTimer;
	public Flower(double x, double y) {
		xPos = (int)x;
		yPos = (int)y;
		shootTick = 0;
		pollinateTimer = 0;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public int getPollinateTimer(){
		return pollinateTimer;
	}
	public void resetPollinateTimer() {
		pollinateTimer = 0;
	}
	public void tick() {
		shootTick++;
		pollinateTimer++;
		try {
			if (Main.distance(xPos, yPos, Main.hurricane.getX(), Main.hurricane.getY())<200) {
				Main.despawnFlower(this);
			}
		} catch (NullPointerException e) {
			
		}
		if (shootTick==40) {
			shootTick = 0;
			for (CO2 c : Main.co2) {
				if (Main.distance(c.getX(), c.getY(), xPos, yPos)<200) {
					double angle = 0;
					try {
						angle = Math.atan((c.getY()-(double)yPos)/(c.getX()-(double)xPos));
					} catch (ArithmeticException e) {
						angle = Math.atan((c.getY()-yPos)/0.01);
					}
					angle+=((int)(Math.random()-0.5));
					if (xPos>c.getX()) {
						angle+=Math.PI;
					}
					Main.lasers.add(new Laser(angle, xPos+(int)(Math.cos(angle)*20), yPos+(int)(Math.sin(angle)*20), 30));
					break;
				}
			}
		}
	}
	public static Graphics drawFlower(Graphics g, int x, int y) {
		g.drawImage(Images.flower, x-15, y-15, 30, 44, null);
		return g;
	}
}
