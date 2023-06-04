import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends Canvas{
	static int xPos;
	static int yPos;
	static int xVel;
	static int yVel;
	static int tempTimer;
	static int co2Timer;
	static int healthTimer;
	static int victoryTimer;
	static int temperature;
	static int health;
	static boolean reload;
	static boolean tripleShot;
	static boolean factoryExists;
	static int gameStage;
	static int calibration;
	static boolean spacePressed;
	static boolean showHowToPlay;
	static boolean firstPoster;
	static double calibrateX;
	static double calibrateY;
	static Factory factory;
	static int factoryTimer;
	static byte difficulty;
	static int co2SpawnRate;
	static int damageFactor;
	static String defaultFont;
	static int defaultSize;
	static int introTick;
	static int guideTick;
	static int tempWarningTimer;
	static Hurricane hurricane;
	static int hurricaneTimer;
	static boolean showMap;
	static boolean instantWin = false;
	static ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
	static ArrayList<Laser> lasers = new ArrayList<Laser>();
	static ArrayList<CO2> co2 = new ArrayList<CO2>();
	static ArrayList<Poster> posters = new ArrayList<Poster>();
	static ArrayList<Flower> flowers = new ArrayList<Flower>();
	static ArrayList<Tree> trees = new ArrayList<Tree>();
	static ArrayList<Blast> bombs = new ArrayList<Blast>();
	static ArrayList<Bee> bees = new ArrayList<Bee>();
	static ArrayList<Wildfire> fires = new ArrayList<Wildfire>();
	static ArrayList<Splash> splashes = new ArrayList<Splash>();
	static ArrayList<Message> messages = new ArrayList<Message>();
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		xPos = 0;
		yPos = 0;
		xVel = 0;
		yVel = 0;
		tempTimer = 0;
		co2Timer = 0;
		healthTimer = 0;
		victoryTimer = 0;
		temperature = 1626;
		health = 100;
		factoryTimer = 0;
		difficulty = 0;
		tempWarningTimer = 0;
		reload = true;
		tripleShot = false;
		firstPoster = true;
		showMap = false;
		Images.loadImages();
		//-----//
		JFrame frame = new JFrame("The Climate Battle");
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Keyboard listener
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				char key = e.getKeyChar();
				switch (key) {
				case 'r': //Debugging purposes
					/*System.out.print(MouseInfo.getPointerInfo().getLocation().getX()-calibrateX);
					System.out.print(" ");
					System.out.println(MouseInfo.getPointerInfo().getLocation().getY()-calibrateY);*/
					break;
				case 'w':
					yVel = -5;
					break;
				case 'a':
					xVel = -5;
					break;
				case 's':
					yVel = 5;
					break;
				case 'd':
					xVel = 5;
					break;
				case 'e':
					Shop.toggleShow();
				case ' ':
					if (gameStage<2) {
						spacePressed = true;
						return;
					}
					if (reload) {
						if (tripleShot) {
							lasers.add(new Laser(getAngle()-0.2, false));
							lasers.add(new Laser(getAngle()+0.2, false));
						}
						lasers.add(new Laser(getAngle(), false));
						reload = false;
					}
					break;
				case 't':
					if (gameStage==1) {
						gameStage = 2;
					}
					break;
				case 'q':
					if (gameStage==1) {
						System.exit(0);
					}
				case 'p':
					if (Shop.getPurchasing()==1) {
						posters.add(new Poster(MouseInfo.getPointerInfo().getLocation().getX()-400+xPos-calibrateX, MouseInfo.getPointerInfo().getLocation().getY()-300+yPos-calibrateY));
						Shop.purchase(0);
						if (firstPoster) {
							messages.add(new Message("Hover your mouse over the poster to collect money", 160));
							firstPoster = false;
						}
					}
					if (Shop.getPurchasing()==2) {
						flowers.add(new Flower(MouseInfo.getPointerInfo().getLocation().getX()-400+xPos-calibrateX, MouseInfo.getPointerInfo().getLocation().getY()-300+yPos-calibrateY));
						Shop.purchase(0);
					}
					if (Shop.getPurchasing()==3) {
						trees.add(new Tree(MouseInfo.getPointerInfo().getLocation().getX()-400+xPos-calibrateX, MouseInfo.getPointerInfo().getLocation().getY()-300+yPos-calibrateY));
						Shop.purchase(0);
					}
					if (Shop.getPurchasing()==4) {
						bombs.add(new Blast(MouseInfo.getPointerInfo().getLocation().getX()-400+xPos-calibrateX, MouseInfo.getPointerInfo().getLocation().getY()-300+yPos-calibrateY));
						Shop.purchase(0);
					}
					break;
				case 'f':
					if (gameStage<2) {
						return;
					}
					double x = MouseInfo.getPointerInfo().getLocation().getX()-calibrateX+xPos-400;
					double y = MouseInfo.getPointerInfo().getLocation().getY()-calibrateY+yPos-300;
					for (int i=0; i<fires.size(); i++) {
						fires.get(i).splash(x, y);
					}
					Audio.playWater();
					splashes.add(new Splash(x, y));
					break;
				case 'o':
					if (gameStage==1) {
						double mouseX = MouseInfo.getPointerInfo().getLocation().getX()-calibrateX;
						double mouseY = MouseInfo.getPointerInfo().getLocation().getY()-calibrateY;
						if (mouseX>15&&mouseX<115&&mouseY>470&&mouseY<495) {
							try {
								java.awt.Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (URISyntaxException e1) {
								e1.printStackTrace();
							}
						}
						break; 
					}
				case 'm':
					if (gameStage==2) {
						showMap = !showMap;
					}
					break;
				case '1':
					if (gameStage==1) {
						difficulty = 0;
						break;
					}
					if (Shop.getPurchasing()==0&&Shop.canPurchase(1)) {
						Shop.purchase(1);
					}
					break;
				case '2':
					if (gameStage==1) {
						difficulty = 1;
						break;
					}
					if (Shop.getPurchasing()==0&&Shop.canPurchase(2)) {
						Shop.purchase(2);
					}
				case '3':
					if (gameStage==1) {
						difficulty = 2;
						break;
					}
					if (Shop.getPurchasing()==0&&Shop.canPurchase(3)) {
						Shop.purchase(3);
					}
					break;
				case '4':
					if (gameStage<2) {
						break;
					}
					if (Shop.getPurchasing()==0&&Shop.canPurchase(4)) {
						Shop.purchase(4);
					}
					break;
				case '5':
					if (gameStage<2) {
						break;
					}
					if (Shop.getPurchasing()==0&&Shop.canPurchase(5)) {
						Shop.purchase(5);
					}
					break;
				case '6':
					if (gameStage<2) {
						break;
					}
					if (Shop.getPurchasing()==0&&Shop.canPurchase(6)) {
						Shop.purchase(6);
					}
					break;
				case '7':
					if (gameStage<2) {
						break;
					}
					if (Shop.getPurchasing()==0&&Shop.canPurchase(7)) {
						Shop.purchase(7);
					}
					break;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				char key = e.getKeyChar();
				switch (key) {
				case 'w':
					yVel = 0;
					break;
				case 'a':
					xVel = 0;
					break;
				case 's':
					yVel = 0;
					break;
				case 'd':
					xVel = 0;
					break;
				case ' ':
					if (gameStage<2) {
						spacePressed = false;
						calibration = 0;
						return;
					}
					reload = true;
				}
			}
		});
		gameStage = -1;
		spacePressed = false;
		calibration = 0;
		guideTick = 0;
		frame.add(panel);
		Canvas canvas = new Main();
		canvas.setSize(800, 600);
		canvas.setBackground(Color.black);
		frame.add(canvas);
		frame.pack();
		canvas.setFocusable(false);
		frame.setVisible(true);
		for (introTick=0; introTick<200; introTick++) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canvas.repaint();
			canvas.paint(canvas.getGraphics());
		}
		gameStage = 0;
		while (gameStage==0) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Audio.music();
			if (spacePressed) {
				calibration-=8;
			}
			canvas.repaint();
			canvas.paint(canvas.getGraphics());
			if (calibration<-360) {
				calibrateX = MouseInfo.getPointerInfo().getLocation().getX()-400;
				calibrateY = MouseInfo.getPointerInfo().getLocation().getY()-300;
				gameStage = 1;
			}
		}
		while (gameStage==1) {
			Audio.music();
			canvas.repaint();
			canvas.paint(canvas.getGraphics());
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (MouseInfo.getPointerInfo().getLocation().getX()-calibrateX>14&&MouseInfo.getPointerInfo().getLocation().getX()-calibrateX<98&&MouseInfo.getPointerInfo().getLocation().getY()-calibrateY>63&&MouseInfo.getPointerInfo().getLocation().getY()-calibrateY<83) {
				showHowToPlay = true;
			} else {
				showHowToPlay = false;
			}
		}
		
		if (difficulty==2) {
			damageFactor = 3;
			co2SpawnRate = 35;
		} else if (difficulty==1) {
			damageFactor = 2;
			co2SpawnRate = 35;
		} else {
			damageFactor = 1;
			co2SpawnRate = 50;
		}
		//Add walls
		for (int i=0; i<10; i++) {
			int x1 = (int)(Math.random()*2000+400);
			int y1 = (int)(Math.random()*1800);
			int x2 = (int)(Math.random()*200+100)+x1;
			int y2 = (int)(Math.random()*200+100)+y1;
			if (x2>2400) {
				x2 = 2400;
			}
			if (y2>1800) {
				y2 = 1800;
			}
			walls.add(new Rectangle(x1, y1, x2, y2));
		}
		//Spawn initial CO2
		for (int i=0; i<50; i++) {
			int x = (int)(Math.random()*2180+100);
			int y = (int)(Math.random()*1580+100);
			co2.add(new CO2(x, y));
		}
		//Create factory
		factory = new Factory();
		messages.add(new Message("Use WASD keys to move", 200));
		//-----//
		while (true) {
			//-----//
			try {
				if (difficulty==2) {
					Thread.sleep(15);
				} else {
					Thread.sleep(25);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canvas.repaint();
			canvas.paint(canvas.getGraphics());
			if (MouseInfo.getPointerInfo().getLocation().getX()-calibrateX>770&&MouseInfo.getPointerInfo().getLocation().getY()-calibrateY>570) {
				continue;
			}
			//-----//
			if (victoryTimer>=1200) {
				gameStage = 3;
			}
			Audio.music();
			tempTimer++;
			co2Timer++;
			healthTimer++;
			if (guideTick<401) {
				guideTick++;
			}
			if (guideTick==200) {
				messages.add(new Message("Use mouse to aim and press space to shoot chloroplasts", 200));
			}
			if (guideTick==400) {
				messages.add(new Message("Press E to view the shop", 200));
			}
			if (co2Timer==co2SpawnRate) {
				int x = (int)(Math.random()*2180+100);
				int y = (int)(Math.random()*1580+100);
				co2.add(new CO2(x, y));
				co2Timer = 0;
			}
			//if (health<100) {health++;} //For debugging
			if (co2.size()>0&&tempTimer>3000/co2.size()) {
				temperature++;
				tempTimer = 0;
			}
			if (temperature>1900) {
				health--;
			}
			if (healthTimer>200&&health<100) {
				health+=1;
				healthTimer = 0;
			}
			if (health>0) {
				xPos+=xVel;
				yPos+=yVel;
			}
			for (int i=0; i<messages.size(); i++) {
				messages.get(i).tick();
			}
			for (int i=0; i<lasers.size(); i++) {
				lasers.get(i).tick();
			}
			for (int i=0; i<co2.size(); i++) {
				co2.get(i).tick();
			}
			for (int i=0; i<bees.size(); i++) {
				bees.get(i).tick();
			}
			for (int i=0; i<splashes.size(); i++) {
				splashes.get(i).tick();
			}
			Map.census();
			if (instantWin&&co2.size()>0) {
				co2.remove(0);
			}
			factoryExists = factory!=null;
			if (factoryExists) {
				factory.tick();
			} else {
				factoryTimer++;
				if (factoryTimer>3600) {
					factory = new Factory();
					factoryExists = true;
					messages.add(new Message("A new factory has been constructed!", 120));
					factoryTimer = 0;
				}
			}
			if (hurricane!=null) {
				hurricane.tick();
				hurricaneTimer--;
				if (hurricaneTimer<=0) {
					hurricane = null;
					messages.add(new Message("The hurricane has passed, for now...", 120));
				}
			} else {
				if (difficulty>0) {
					hurricaneTimer++;
				}
				if (hurricaneTimer>=3600-temperature) {
					hurricane = new Hurricane();
					hurricaneTimer = 2000;
					messages.add(new Message("Watch out! A hurricane has formed!", 120));
				}
			}
			for (int i=0; i<posters.size(); i++) {
				posters.get(i).tick();
				double mouseX = MouseInfo.getPointerInfo().getLocation().getX()-400+xPos-calibrateX;
				double mouseY = MouseInfo.getPointerInfo().getLocation().getY()-300+yPos-calibrateY;
				if (mouseX>posters.get(i).getX()&&mouseX<posters.get(i).getX()+60&&mouseY>posters.get(i).getY()&&mouseY<posters.get(i).getY()+45) {
					posters.get(i).collectMoney();
				}
			}
			for (int i=0; i<flowers.size(); i++) {
				flowers.get(i).tick();
			}
			for (int i=0; i<trees.size(); i++) {
				trees.get(i).tick();
			}
			for (int i=0; i<bombs.size(); i++) {
				bombs.get(i).tick();
			}
			if (difficulty==2) {
				Wildfire.tick();
				for (int i=0; i<fires.size(); i++) {
					fires.get(i).fireTick();
				}
			}
			collision();
		}
	}
	public void paint(Graphics g) {
		if (defaultFont==null) {
			defaultFont = g.getFont().getName();
			defaultSize = g.getFont().getSize();
		}
		if (gameStage==-1) {
			if (introTick<40) {
			} else if (introTick<120){
				g.setColor(Color.green);
				g.setFont(new Font("Arial Black", 0, 150));
				g.drawString("THE", 30, 150);
				g.drawString("CLIMATE", 30, 350);
				g.drawString("BATTLE", 30, 550);
			} else if (introTick<160) {
				g.setColor(rgb(0, (160-introTick)*6.4, 0));
				g.setFont(new Font("Arial Black", 0, 150));
				g.drawString("THE", 30, 150);
				g.drawString("CLIMATE", 30, 350);
				g.drawString("BATTLE", 30, 550);
			}
			return;
		}
		if (gameStage==0) {
			g.setFont(new Font(defaultFont, 0, defaultSize));
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.black);
			g.drawString("Move you mouse to the cross in the center of the screen, then hold down the space bar to calibrate your mouse position.", 20, 20);
			g.drawString("Then, try not to move this window around or the mouse pointer will be off.", 20, 40);
			g.setColor(Color.green);
			g.fillArc(380, 280, 40, 40, 90, calibration);
			g.setColor(Color.black);
			g.drawOval(380, 280, 40, 40);
			g.drawLine(400, 295, 400, 305);
			g.drawLine(395, 300, 405, 300);
			return;
		}
		if (gameStage==1) {
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.black);
			g.setFont(new Font(defaultFont, 0, defaultSize*2));
			g.drawString("Main menu", 20, 40);
			g.setFont(new Font(defaultFont, 0, defaultSize));
			g.drawString("Select difficulty", 20, 140);
			if (difficulty==0) {
				g.setColor(Color.black);
				g.drawString("Factory lasers do less damage", 20, 250);
				g.drawString("No hurricanes", 20, 280);
				g.drawString("CO2 is produced slower", 20, 310);
				g.setColor(Color.green);
			} else {
				g.setColor(Color.black);
			}
			g.drawString("Easy (Press 1)", 20, 160);
			if (difficulty==1) {
				g.setColor(Color.black);
				g.drawString("Factory lasers do normal damage", 20, 250);
				g.drawString("CO2 is produced faster", 20, 280);
				g.setColor(Color.yellow);
			} else {
				g.setColor(Color.black);
			}
			g.drawString("Medium (Press 2)", 20, 180);
			if (difficulty==2) {
				g.setColor(Color.black);
				g.drawString("Factory lasers do more damage", 20, 250);
				g.drawString("Wildfires can erupt", 20, 280);
				g.drawString("CO2 is produced faster", 20, 310);
				g.drawString("Game is faster-paced", 20, 340);
				g.setColor(Color.red);
			} else {
				g.setColor(Color.black);
			}
			g.drawString("Hard (Press 3)", 20, 200);
			g.setColor(Color.blue);
			g.fillRect(15, 470, 100, 25);
			g.setColor(Color.orange);
			g.drawString("Learn More", 20, 485);
			double mouseX = MouseInfo.getPointerInfo().getLocation().getX()-calibrateX;
			double mouseY = MouseInfo.getPointerInfo().getLocation().getY()-calibrateY;
			if (mouseX>15&&mouseX<115&&mouseY>470&&mouseY<495) {
				g.setColor(Color.black);
				g.drawString("Press O to open website", 120, 485);
			}
			g.setColor(Color.green);
			g.setFont(new Font("Arial Black", 0, defaultSize*4));
			g.drawString("THE CLIMATE", 275, 350);
			g.drawString("BATTLE", 350, 430);
			g.setFont(new Font("Arial Black", 0, defaultSize*2));
			g.setColor(Color.magenta);
			g.drawString("Press T to Start", 350, 500);
			g.setFont(new Font(defaultFont, 0, defaultSize));
			g.setColor(Color.black);
			g.drawString("Music and sound effects", 25, 550);
			g.drawString("by zapsplat.com", 25, 570);
			if (showHowToPlay) {
				g.drawString("Use WASD keys to move", 300, 20);
				g.drawString("Use mouse pointer to aim", 300, 50);
				g.drawString("Press space to shoot chloroplasts", 300, 80);
				g.drawString("Shoot the CO2 molecules", 300, 110);
				g.drawString("Press E to view the shop", 300, 140);
				g.drawString("Destroy factories because they produce CO2", 300, 170);
				g.drawString("Watch out for hurricanes", 300, 200);
				g.drawString("Press M to view the satellite map", 300, 230);
				g.drawString("Reduce the CO2 to a safe level before the temperature gets too high!", 300, 260);
				g.setColor(Color.yellow);
				g.drawString("How to Play", 20, 80);
			} else {
				g.drawString("How to Play", 20, 80);
			}
			return;
		}
		if (gameStage==3) {
			g.setColor(Color.green);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.black);
			g.setFont(new Font("Arial Black", 0, 120));
			g.drawString("YOU WON!", 20, 150);
			g.setFont(new Font(defaultFont, 0, defaultSize));
			g.drawString("You saved this digital planet, now try helping your planet in the Climate Battle ;)", 50, 300);
			g.drawString("Your score: "+(19000-temperature*10)*(int)Math.pow(2, difficulty), 50, 400);
			Audio.playWin();
			try {
				Thread.sleep(5000);
				System.exit(0);
			} catch (InterruptedException e) {
				System.exit(0);
			}
		}
		g.setColor(Color.black);
		if (health<1) {
			g.setColor(Color.red);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.black);
			g.setFont(new Font("Arial Black", 0, 120));
			g.drawString("You died!", 10, 150);
			g.setFont(new Font(defaultFont, 0, defaultSize));
			g.drawString("Re-launch the program to try again.", 50, 300);
			Audio.playLose();
			try {
				Thread.sleep(5000);
				System.exit(0);
			} catch (InterruptedException e) {
				System.exit(0);
			}
		}
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.white);
		g.fillOval(385, 285, 30, 30);
		g.setColor(Color.black);
		g.drawOval(385, 285, 30, 30);
		g.drawRect(xPos*-1+385, yPos*-1+285, 2400, 1800);
		g.setColor(Color.darkGray);
		try {
			for (Rectangle r : walls) {
				g.fillRect(r.getX1()-xPos+385, r.getY1()-yPos+285, r.getX2()-r.getX1(), r.getY2()-r.getY1());
			}
			for (Poster p : posters) {
				g = p.drawPoster(g, p.getX()-xPos+400, p.getY()-yPos+300);
			}
			for (Flower f : flowers) {
				g = Flower.drawFlower(g, f.getX()-xPos+400, f.getY()-yPos+300);
			}
			for (Tree t : trees) {
				g = Tree.drawTree(g, t.getX()-xPos+400, t.getY()-yPos+300);
			}
			for (Blast b : bombs) {
				g = b.drawBlast(g, b.getX()-xPos+400, b.getY()-yPos+300);
			}
			for (Bee b : bees) {
				g.drawImage(Images.bee, b.getX()-Main.xPos+390, b.getY()-Main.yPos+290, 20, 21, null);
			}
			for (Wildfire f : fires) {
				g.drawImage(Images.fire, f.getX()-Main.xPos+388, f.getY()-Main.yPos+285, 24, 30, null);
			}
			g.setColor(Color.cyan);
			for (Splash s : splashes) {
				g.fillOval(s.getX()-s.getTimer()-Main.xPos+400, s.getY()-s.getTimer()-Main.yPos+300, s.getTimer()*2, s.getTimer()*2);
			}
			for (Laser l : lasers) {
				if (l.isEnemy()) {
					g.setColor(Color.getHSBColor(0, (float)0.5, (float)0.6));
					double startX = l.getX()-Math.cos(l.getAngle())*30;
					double startY = l.getY()-Math.sin(l.getAngle())*30;
					g.drawLine((int)(startX-xPos+400), (int)(startY-yPos+300), l.getX()-xPos+400, l.getY()-yPos+300);
				} else {
					g.setColor(Color.green);
					g.fillOval(l.getX()-xPos+395, l.getY()-yPos+295, 10, 10);
				}
				
			}
			for (CO2 c : co2) {
				g.drawImage(Images.co2, c.getX()-xPos+385, c.getY()-yPos+285, 30, 30, null);
			}
		} catch (ConcurrentModificationException e) {
			
		}
		if (factoryExists) {
			g.setColor(Color.red);
			g.fillRect(factory.getX()-xPos+400, factory.getY()-yPos+270, factory.getHealth()*2, 20);
			g.setColor(Color.black);
			g.drawRect(factory.getX()-xPos+400, factory.getY()-yPos+270, 200, 20);
			g.drawImage(Images.factory, factory.getX()-xPos+400, factory.getY()-yPos+300, 200, 200, null);
		}
		if (hurricane!=null) {
			g.drawImage(Images.hurricane, hurricane.getX()-xPos+250, hurricane.getY()-yPos+150, 300, 324, null);
		}
		if (showMap) {
			g = Map.drawMap(g);
		}
		if (temperature>1900) {
			g.setColor(Color.red);
		} else if (temperature>1765){
			g.setColor(Color.orange);
		} else {
			g.setColor(Color.yellow);
		}
		g.drawString("Average temperature: "+(temperature/100.0)+" C", 20, 20);
		if (temperature>1880) {
			Audio.playAlarm();
			tempWarningTimer++;
			if (tempWarningTimer==80) {
				tempWarningTimer = 0;
			}
			if (tempWarningTimer<40) {
				g.setColor(Color.white);
				g.fillRect(220, 6, 210, 20);
			}
			g.setColor(Color.red);
			g.drawString("Dangerously high temperature!", 230, 20);
		}
		g.setColor(Color.blue);
		g.drawString("CO2 concentration: "+co2.size(),20, 50);
		if (co2.size()<30) {
			g.setColor(Color.green);
			g.drawString("Good job! Keep the carbon dioxide concentration under 30 to win the game.", 20, 80);
			victoryTimer++;
			g.fillArc(20, 90, 30, 30, 90, victoryTimer*-3/10);
			g.setColor(Color.black);
			g.drawOval(20, 90, 30, 30);
		} else {
			victoryTimer = 0;
		}
		g.setColor(Color.red);
		g.fillRect(580, 20, health*2, 20);
		g.fillPolygon(drawCannon());
		g.setColor(Color.black);
		g.drawRect(580, 20, 200, 20);
		if (Shop.isShowing()) {
			g = Shop.drawShop(g);
		}
		if (Shop.getPurchasing()==1) {
			g = Poster.firstPoster(g, (int)(MouseInfo.getPointerInfo().getLocation().getX()-calibrateX), (int)(MouseInfo.getPointerInfo().getLocation().getY()-calibrateY));
		}
		if (Shop.getPurchasing()==2) {
			g = Flower.drawFlower(g, (int)(MouseInfo.getPointerInfo().getLocation().getX()-calibrateX), (int)(MouseInfo.getPointerInfo().getLocation().getY()-calibrateY));
		}
		if (Shop.getPurchasing()==3) {
			g = Tree.drawTree(g, (int)(MouseInfo.getPointerInfo().getLocation().getX()-calibrateX), (int)(MouseInfo.getPointerInfo().getLocation().getY()-calibrateY));
		}
		if (Shop.getPurchasing()==4) {
			g = Blast.placeBlast(g, (int)(MouseInfo.getPointerInfo().getLocation().getX()-calibrateX), (int)(MouseInfo.getPointerInfo().getLocation().getY()-calibrateY));
		}
		for (int i=0; i<messages.size(); i++) {
			g = messages.get(i).drawMessage(g, 570-i*30);
		}
		if (MouseInfo.getPointerInfo().getLocation().getX()>760&&MouseInfo.getPointerInfo().getLocation().getY()>570) {
			g.setColor(Color.blue);
			g.fillOval(770, 570, 30, 30);
			g.setColor(Color.black);
			Polygon triangle = new Polygon();
			triangle.addPoint(779, 575);
			triangle.addPoint(791, 585);
			triangle.addPoint(779, 595);
			g.fillPolygon(triangle);
		} else {
			g.setColor(Color.blue);
			g.fillOval(770, 570, 30, 30);
			g.setColor(Color.black);
			g.fillRect(777, 578, 5, 14);
			g.fillRect(787, 578, 5, 14);
		}
		
	}
	public static void collision() {
		if (xPos<0) {
			xVel = 0;
			xPos = 0;
		}
		if (xPos>2370) {
			xVel = 0;
			xPos = 2370;
		}
		if (yPos<0) {
			yVel = 0;
			yPos = 0;
		}
		if (yPos>1770) {
			yVel = 0;
			yPos = 1770;
		}
		for (Rectangle r : walls) {
			if (xPos>r.getX1()-30&&xPos<r.getX2()&&yPos>r.getY1()-30&&yPos<r.getY2()) {
				xPos-=xVel;
				xVel = 0;
				yPos-=yVel;
				yVel = 0;
			}
		}
	}
	public static double getAngle() {
		double x = MouseInfo.getPointerInfo().getLocation().getX()-400-calibrateX;
		double y = MouseInfo.getPointerInfo().getLocation().getY()-300-calibrateY;
		double angle = (Math.atan(y/x));
		if (x<0) {
			angle+=Math.PI;
		}
		return angle;
	}
	public static Polygon drawCannon() {
		Polygon cannon = new Polygon();
		cannon.addPoint(400, 300);
		double x = Math.cos(getAngle()-0.1)*50+400;
		double y = Math.sin(getAngle()-0.1)*50+300;
		cannon.addPoint((int)x, (int)y);
		x = Math.cos(getAngle()+0.1)*50+400;
		y = Math.sin(getAngle()+0.1)*50+300;
		cannon.addPoint((int)x, (int)y);
		return cannon;
	}
	public static void despawnLaser(Laser l) {
		lasers.remove(l);
	}
	public static void despawnCO2(CO2 c) {
		co2.remove(c);
	}
	public static void despawnBee(Bee b) {
		bees.remove(b);
	}
	public static void despawnFlower(Flower f) {
		flowers.remove(f);
	}
	public static void despawnTree(Tree t) {
		trees.remove(t);
	}
	public static void despawnSplash(Splash s) {
		splashes.remove(s);
	}
	public static void blast(Blast b) {
		bombs.remove(b);
	}
	public static void despawnFire(Wildfire w) {
		fires.remove(w);
	}
	public static void removeMessage(Message m) {
		messages.remove(m);
	}
	public static double distance(double x1, double y1, double x2, double y2) {
		double dx = x2-x1;
		double dy = y2-y1;
		return Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
	}
	public static Color rgb(double r, double g, double b) {
		float[] hsb = Color.RGBtoHSB((int)r, (int)g, (int)b, null);
		return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
}
