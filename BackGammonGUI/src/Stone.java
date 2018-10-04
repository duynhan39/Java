import javax.swing.*;
import java.awt.*;

/**
 * Created by duynhan on 4/15/18.
 */
public class Stone {
    int player;
    Stone() {};
    Stone(int player) { this.player = player; }

//    @Override
//    public void paint(Graphics g) {
//
//
////        g.setColor(Color.YELLOW);
////        int size = Math.min(getWidth(), getHeight());
////
////        int x = getWidth()/2 - size/2;
////        int y = getHeight()/2 - size/2;
////
////        g.drawOval(x, y, size, size);
////        //g.fillOval(x, y, size, size);
////
////        int total = 0;
////        for(BoundedRangeModel each : models) {
////            total += each.getValue();
////        }
////
////        Color c[] = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
////
////        int currentAngle = 0;
////
////        g.setColor(c[c.length-1]);
////        g.fillOval(x, y, size, size);
////
////        for(int i = 0; i < 4; i++) {
////            int newAngle = models[i].getValue() * 360 / total ;
////            g.setColor(c[i]);
////            g.drawArc(x, y, size, size, currentAngle, newAngle);
////            g.fillArc(x, y, size, size, currentAngle, newAngle);
////            currentAngle += newAngle;
////        }
//
//    }
}
