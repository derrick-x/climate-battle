
public class Wildfire {
	int xPos;
	int yPos;
	int co2Timer;
	static int fireTimer = 0;
	public Wildfire(double x, double y) {
		xPos = (int)x;
		yPos = (int)y;
		co2Timer = 0;
	}
	public int getX(){
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public void fireTick() {
		co2Timer++;
		if (co2Timer==600) {
			Main.co2.add(new CO2(xPos, yPos));
		}
		if (Math.random()<0.002) {
			double angle = Math.random()*2*Math.PI;
			double targetX = xPos+Math.cos(angle)*30;
			double targetY = yPos+Math.sin(angle)*30;
			boolean canPlace = true;
			if (targetX<0||targetX>2400) {
				canPlace = false;
			}
			if (targetY<0||targetY>1800) {
				canPlace = false;
			}
			for (Wildfire w : Main.fires) {
				if (Main.distance(targetX, targetY, w.getX(), w.getY())<15) {
					canPlace = false;
				}
			}
			if (canPlace) {
				Main.fires.add(new Wildfire(targetX, targetY));
			}
		}
	}
	public void splash(double x, double y) {
		if (Main.distance(xPos, yPos, x, y)<100) {
			Main.despawnFire(this);
		}
	}
	public static void tick() {
		fireTimer++;
		if (fireTimer>=3626-Main.temperature) {
			Main.fires.add(new Wildfire(Math.random()*2400, Math.random()*1800));
			fireTimer = 0;
		}
	}
}
