
public class Factory {
	int xPos;
	int yPos;
	int health;
	int shootTimer;
	public Factory() {
		xPos = (int)(Math.random()*1800+400);
		yPos = (int)(Math.random()*1200+400);
		health = 100;
		shootTimer = 0;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public int getHealth() {
		return health;
	}
	public void tick() {
		if (health<1) {
			Audio.playExplode();
			Main.factoryExists = false;
			Main.factory = null;
			return;
		}
		shootTimer++;
		if (shootTimer>30&&Main.distance(xPos, yPos, Main.xPos+15, Main.yPos+15)<500&&health>0) {
			shootTimer = 0;
			double angle = 0;
			try {
				angle = Math.atan((Main.yPos-yPos)/(Main.xPos-xPos+0.0));
			} catch (ArithmeticException e) {
				angle = (int)Math.atan((Main.yPos-yPos)/0.01);
			}
			if (xPos>Main.xPos) {
				angle+=Math.PI;
			}
			if (xPos<Main.xPos+15) {
				//angle+=Math.PI;
			}
			Main.lasers.add(new Laser(angle, true));
		}
		if (Math.random()<0.04) {
			Main.co2.add(new CO2(((int)Math.random())*200+xPos, ((int)(Math.random())*200+yPos)));
		}
		for (int i=0; i<Main.lasers.size(); i++) {
			if (!Main.lasers.get(i).isEnemy()) {
				if (Main.lasers.get(i).getX()>xPos&&Main.lasers.get(i).getX()<xPos+200&&Main.lasers.get(i).getY()>yPos+80&&Main.lasers.get(i).getY()<yPos+200) {					
					health-=4;
					if (health<1) {
						Audio.playExplode();
						Shop.changeMoney(200);
					} else {
						Audio.playHit();
					}
					Main.despawnLaser(Main.lasers.get(i));
				}
			}
		}
	}
}
