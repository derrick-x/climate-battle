
public class Bee {
	double xPos;
	double yPos;
	boolean hasPollen;
	double xVel;
	double yVel;
	int time;
	public Bee() {
		time = 0;
		xPos = Main.xPos;
		yPos = Main.yPos;
		xVel = (int)(Math.random()*11)-5;
		yVel = (int)(Math.random()*11)-5;
	}
	public int getX() {
		return (int)xPos;
	}
	public int getY() {
		return (int)yPos;
	}
	public void tick() {
		time++;
		xPos+=xVel;
		yPos+=yVel;
		if (xPos<10) {
			xVel*=-1;
			xPos+=xVel;
		}
		if (xPos>2390) {
			xVel*=-1;
			xPos+=xVel;
		}
		if (yPos<10) {
			yVel*=-1;
			yPos+=yVel;
		}
		if (yPos>1790) {
			yVel*=-1;
			yPos+=yVel;
		}
		for (Flower f : Main.flowers) {
			if (Main.distance(xPos, yPos, f.getX(), f.getY())<20&&f.getPollinateTimer()>600) {
				hasPollen = true;
				f.resetPollinateTimer();
			}
		}
		if (Main.distance(xPos, yPos, Main.xPos, Main.yPos)<300&&Math.random()<0.005) {
			Audio.playBuzz();
		}
		if (time==200) {
			time = 0;
			if (hasPollen) {
				Main.flowers.add(new Flower(xPos, yPos));
				hasPollen = false;
			}
			try {
				if (Main.distance(xPos, yPos, Main.hurricane.getX(), Main.hurricane.getY())<200){
					Main.despawnBee(this);
				}
			} catch (NullPointerException e) {
				
			}
			for (Flower f : Main.flowers) {
				double distance = Main.distance(xPos, yPos, f.getX(), f.getY());
				if (distance<200) {
					double dx = f.getX()-xPos;
					double dy = f.getY()-yPos;
					double angle = 0;
					try {
						angle = Math.atan(dy/dx);
					} catch (ArithmeticException e) {
						angle = Math.atan(dy/0.01);
					}
					if (dx>0) {
						angle+=Math.PI;
					}
					xVel = Math.cos(angle)*-5;
					yVel = Math.sin(angle)*-5;
					return;
				}
			}
			xVel = (int)(Math.random()*11)-5;
			yVel = (int)(Math.random()*11)-5;
		}
	}
}
