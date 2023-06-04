import java.awt.Graphics;
import java.awt.Color;

public class Map{
    static int[][] data = new int[45][60];
    static int timer = 0; //Debugging
    public static void census(){
    	data = new int[15][20];
        for (int i=0; i<Main.co2.size(); i++){
            int x = Main.co2.get(i).getX()/120;
            int y = Main.co2.get(i).getY()/120;
            data[y][x]++;
        }
    }
    public static Graphics drawMap(Graphics g){
        for (int y=0; y<15; y++){
            for (int x=0; x<20; x++){
                g.setColor(Color.getHSBColor(0, (float)(data[y][x]*0.2), 1));
                g.fillRect(10+x*6, 400+y*6, 6, 6);
            }
        }
        g.setColor(Color.black);
        g.drawRect(10, 400, 120, 90);
        g.setColor(Color.green);
        g.fillOval(Main.xPos/20+7, Main.yPos/20+397, 6, 6);
        return g;
    }
}