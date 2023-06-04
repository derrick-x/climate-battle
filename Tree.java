import java.awt.Graphics;

public class Tree {
	int xPos;
	int yPos;
	int shootTick;
	public Tree(double x, double y) {
		xPos = (int)x;
		yPos = (int)y;
		shootTick = 0;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public void tick() {
		shootTick++;
		try {
			if (Main.distance(xPos, yPos, Main.hurricane.getX(), Main.hurricane.getY())<200){
				Main.despawnTree(this);
			}
		} catch (NullPointerException e) {
			
		}
		if (shootTick==20) {
			shootTick = 0;
			for (CO2 c : Main.co2) {
				if (Main.distance(c.getX(), c.getY(), xPos, yPos)<600) {
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
					Main.lasers.add(new Laser(angle, xPos+(int)(Math.cos(angle)*20), yPos+(int)(Math.sin(angle)*20), 100));
					break;
				}
			}
		}
	}
	public static Graphics drawTree(Graphics g, int x, int y) {
		g.drawImage(Images.tree, x-100, y-100, 200, 200, null);
		return g;
	}
}
