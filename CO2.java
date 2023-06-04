
public class CO2 {
	int xPos;
	int yPos;
	int xVel;
	int yVel;
	public CO2(int x, int y) {
		xPos = x;
		yPos = y;
		xVel = (int)(Math.random()*9-4);
		yVel = (int)(Math.random()*9-4);
	}
	public void tick() {
		xPos+=xVel;
		yPos+=yVel;
		//Hit by laser?
		for (int i=0; i<Main.lasers.size(); i++) {
			if (Main.lasers.get(i).getX()>xPos-10&&Main.lasers.get(i).getX()<xPos+20&&Main.lasers.get(i).getY()>yPos-10&&Main.lasers.get(i).getY()<yPos+20&&(!Main.lasers.get(i).isEnemy())) {
				Main.despawnLaser(Main.lasers.get(i));
				Audio.playPop();
				Main.despawnCO2(this);
			}
		}
		//Touching player?
		if (Main.distance(xPos+10, yPos+10, Main.xPos+15, Main.yPos+15)<15) {
			Main.health-=15;
			Audio.playPop();
			Main.despawnCO2(this);
		}
		if (xPos<0) {
			xVel*=-1;
			xPos = 0;
		}
		if (xPos>2380) {
			xVel*=-1;
			xPos = 2380;
		}
		if (yPos<0) {
			yVel*=-1;
			yPos = 0;
		}
		if (yPos>1780) {
			yVel*=-1;
			yPos = 1780;
		}
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
}
