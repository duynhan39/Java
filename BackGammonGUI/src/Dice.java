import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by duynhan on 4/1/18.
 */
public class Dice {
    int num;

    Dice() { num = 6; }

    Dice(Dice dice) {
        this.num = dice.num;
    }

    public void Roll() throws InterruptedException {
        Random rand = new Random(System.currentTimeMillis());
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));

        rand = new Random(System.currentTimeMillis());
        num = rand.nextInt(6) + 1;
    }

    public int getNum(){
        return num;
    }

    public void paint(Graphics g, int x, int y, int size) {
        g.setColor(Color.white);
        g.fillRect(x, y, size, size);

        paintDot(g, x, y, size, num);
    }

    public void paintDot(Graphics g, int x, int y, int size, int num) {
        g.setColor(Color.black);
        switch (num) {
            case 1:
                g.fillOval(x + size*4/10, y + size*4/10, size/5, size/5);
                break;
            case 2:
                g.fillOval(x + size*1/10, y + size*1/10, size/5, size/5);
                g.fillOval(x + size*7/10, y + size*7/10, size/5, size/5);
                break;
            case 3:
                paintDot(g, x, y, size, 1);
                paintDot(g, x, y, size, 2);
                break;
            case -2:
                g.fillOval(x + size*1/10, y + size*7/10, size/5, size/5);
                g.fillOval(x + size*7/10, y + size*1/10, size/5, size/5);
                break;
            case 4:
                paintDot(g, x, y, size, -2);
                paintDot(g, x, y, size, 2);
                break;
            case 5:
                paintDot(g, x, y, size, 1);
                paintDot(g, x, y, size, 4);
                break;
            case 6:
                int y0 = y + size * 1 / 10;
                for(int i=0; i<3; i++, y0 += size * 3/10) {
                    g.fillOval(x + size * 2 / 10, y0, size / 5, size / 5);
                    g.fillOval(x + size * 6 / 10, y0, size / 5, size / 5);
                }
                break;


        }
    }
}
