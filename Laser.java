
public class Laser {
	double xPos;
	double yPos;
	double angle;
	boolean enemy;
	int lifespan;
	public Laser(double a, int x, int y, int l) {
		enemy = false;
		lifespan = l;
		angle = a;
		xPos = x;
		yPos = y;
	}
	public Laser(double a, boolean e) {
		enemy = e;
		lifespan = 30;
		angle = a;
		if (enemy) {
			xPos = Main.factory.getX()+Math.cos(angle)*20;
			yPos = Main.factory.getY()+Math.sin(angle)*20;
		} else {
			xPos = Main.xPos+Math.cos(angle)*20;
			yPos = Main.yPos+Math.sin(angle)*20;
		}
	}
	public void tick() {
		xPos+=Math.cos(angle)*15;
		yPos+=Math.sin(angle)*15;
		lifespan--;
		for (Rectangle r : Main.walls) {
			if (xPos>r.getX1()&&xPos<r.getX2()&&yPos>r.getY1()&&yPos<r.getY2()&&(!enemy)&&lifespan<=30) {
				Main.despawnLaser(this);
			}
		}
		if (enemy&&Main.distance((int)xPos, (int)yPos, Main.xPos+15, Main.yPos+15)<25) {
			Main.health-=15*Main.damageFactor;
			Audio.playDamage();
			Main.despawnLaser(this);
		}
		if (lifespan==0) {
			Main.despawnLaser(this);
		}
	}
	public int getX() {
		return (int)xPos;
	}
	public int getY() {
		return (int)yPos;
	}
	public double getAngle() {
		return angle;
	}
	public boolean isEnemy() {
		return enemy;
	}
}
