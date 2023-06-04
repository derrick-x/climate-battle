import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Shop{
	static int posterCost = 20;
	static int flowerCost = 50;
	static int treeCost = 100;
	static int blastCost = 30;
	static int healthCost = 10;
	static int tripleCost = 200;
	static int beesCost = 50;
	static int money = 100;
	static boolean showing = false;
	static int purchasing = 0;
	static boolean placeTip = false;
	public static void toggleShow() {
		showing = !showing;
	}
	public static boolean isShowing() {
		return showing;
	}
	public static int getMoney() {
		return money;
	}
	public static void changeMoney(int amount) {
		money+=amount;
	}
	public static int getPurchasing() {
		return purchasing;
	}
	public static void purchase(int item) {
		purchasing = item;
		if (item==1) {
			money-=20;
			if (!placeTip) {
				Main.messages.add(new Message("Press p to place the item", 120));
				placeTip = true;
			}
		}
		if (item==2) {
			money-=50;
			if (!placeTip) {
				Main.messages.add(new Message("Press p to place the item", 120));
				placeTip = true;
			}
		}
		if (item==3) {
			money-=100;
			if (!placeTip) {
				Main.messages.add(new Message("Press p to place the item", 120));
				placeTip = true;
			}
		}
		if (item==4) {
			money-=30;
			if (!placeTip) {
				Main.messages.add(new Message("Press p to place the item", 120));
				placeTip = true;
			}
		}
		if (item==5) {
			money-=10;
			Main.health+=20;
			if (Main.health>100) {
				Main.health = 100;
			}
			purchasing = 0;
		}
		if (item==6) {
			money-=200;
			Main.tripleShot = true;
			purchasing = 0;
		}
		if (item==7) {
			money-=50;
			for (int i=0; i<5; i++) {
				Main.bees.add(new Bee());
				purchasing = 0;
			}
		}
	}
	public static boolean canPurchase(int item) {
		switch (item) {
		case 1:
			return money>=posterCost;
		case 2:
			return money>=flowerCost;
		case 3:
			return money>=treeCost;
		case 4:
			return money>=blastCost;
		case 5:
			return money>=healthCost;
		case 6:
			return money>=tripleCost&&!Main.tripleShot;
		case 7:
			return money>=beesCost;
		default:
			return false;
		}
	}
	public static Graphics drawShop(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(630, 50, 150, 530);
		g.setColor(Main.rgb(0, 170, 51));
		g.setFont(new Font("Arial Black", 0, (int)(Main.defaultSize*1.5)));
		g.drawString("Shop", 645, 80);
		g.setFont(new Font(Main.defaultFont, 0, Main.defaultSize));
		g.drawString("Money: $"+money, 640, 105);
		g.drawString("Poster: $20", 640, 130);
		g.drawString("Press 1 to purchase", 640, 150);
		g.drawString("Flower: $50", 640, 180);
		g.drawString("Press 2 to purchase", 640, 200);
		g.drawString("Tree: $100", 640, 230);
		g.drawString("Press 3 to purchase", 640, 250);
		g.drawString("Chloro-blast: $30", 640, 280);
		g.drawString("Press 4 to purchase", 640, 300);
		g.drawString("Health boost: $10", 640, 330);
		g.drawString("Press 5 to purchase", 640, 350);
		g.drawString("Triple shot: $200", 640, 380);
		if (Main.tripleShot) {
			g.drawString("Already purchased", 640, 400);
		} else {
			g.drawString("Press 6 to purchase", 640, 400);
		}
		g.drawString("Bees: $50", 640, 430);
		g.drawString("Press 7 to purchase", 640, 450);
		return g;
	}
}
