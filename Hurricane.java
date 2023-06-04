
public class Hurricane {
	int xPos;
	int yPos;
	int xVel;
	int yVel;
	double damage;
	int windTimer;
	public Hurricane() {
		xPos = (int)(Math.random()*2100)+150;
		yPos = (int)(Math.random()*1500)+150;
		xVel = (int)(Math.random()*3)+1;
		yVel = (int)(Math.random()*3)+1;
		if (Math.random()<0.5) {
			xVel*=-1;
		}
		if (Math.random()<0.5) {
			yVel*=-1;
		}
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public void tick() {
		xPos+=xVel;
		yPos+=yVel;
		if (windTimer>0) {
			windTimer--;
		}
		if (Main.distance(xPos, yPos, Main.xPos, Main.yPos)<800) {
			if (windTimer==0) {
				Audio.playWind();
				windTimer = 200;
			}
		}
		if (Main.distance(xPos, yPos, Main.xPos, Main.yPos)<350) {
			double strength = 350-Main.distance(xPos, yPos, Main.xPos, Main.yPos);
			damage+=strength/350;
			if (damage>=1) {
				Main.health--;
				damage--;
			}
			if (xPos<Main.xPos) {
				Main.xPos-=(int)(strength/80);
			}
			if (yPos<Main.yPos) {
				Main.yPos-=(int)(strength/80);
			}
			if (xPos>Main.xPos) {
				Main.xPos+=(int)(strength/80);
			}
			if (yPos>Main.yPos) {
				Main.yPos+=(int)(strength/80);
			}
		}
		if (xPos<150||xPos>2250) {
			xVel*=-1;
		}
		if (yPos<150||yPos>1650) {
			yVel*=-1;
		}
	}
}
