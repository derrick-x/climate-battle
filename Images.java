import java.awt.Toolkit;
import java.awt.Image;

public class Images {
	static Image flower;
	static Image tree;
	static Image co2;
	static Image factory;
	static Image hurricane;
	static Image bee;
	static Image fire;
	public static void loadImages() {
		flower = getImage("flower.png");
		tree = getImage("tree.png");
		co2 = getImage("co2.png");
		factory = getImage("factory.png");
		hurricane = getImage("hurricane.png");
		bee = getImage("Bee.png");
		fire = getImage("fire.png");
	}
	private static Image getImage(String name) {
		return Toolkit.getDefaultToolkit().getImage(name);
	}
}
