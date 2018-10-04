import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private Color dark = new Color(29, 37, 41);
    private Color darkblue = new Color(15, 55, 62);
    private Color salmon = new Color(236, 73, 82);

    private int anchorD;
    private int gap;

    private int triWid;
    private int triHei;

    private int nameHeight;

    private Graphics g;
    private GameBG data;

    public int getAnchorD() {
        return anchorD;
    }

    public int getGap() {
        return gap;
    }

    public int getTriWid() {
        return triWid;
    }

    public int getTriHei() {
        return triHei;
    }

    public int getNameHeight() {
        return nameHeight;
    }

    public void setGame(GameBG data) {
        this.data = data;
        repaint();
    }

    @Override
    public void paint(Graphics graphic) {
        g = graphic;

        if(data.isOver()) {
            g.setColor(salmon);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.white);
            g.setFont(new Font("Avenir", Font.BOLD, getHeight()/5 ));
            g.drawString("Player "+data.player+" wins!!!", 0, getHeight()/2);
        } else {
            data.board.setGraphics(g);
            data.board.setBoardView(this);

            anchorD = Math.min(getWidth() * 9, getHeight() * 18);
            gap = anchorD / 5 / 50;
            nameHeight = gap / 2;
            triWid = (anchorD / 9 - gap * 4) / 12;
            triHei = triWid * 25 / 9;

            g.setColor(darkblue);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(dark);
            g.fillRect(
                    getWidth() / 2 - anchorD / 9 / 2 + gap,
                    getHeight() / 2 - anchorD / 15 / 2 + gap + nameHeight,
                    anchorD / 9 - gap * 2,
                    anchorD / 15 - gap * 2
            );

            g.setColor(darkblue);
            g.fillRect(
                    getWidth() / 2 - gap,
                    0,
                    gap * 2,
                    getHeight()
            );
            data.board.paint();
            int x, y, size;
            size = gap;

            y = getHeight() / 2 + nameHeight - size / 2;
            x = getWidth() / 2 - (anchorD / 9 / 2 - gap * 2);

            for (Dice each : data.getDice()) {
                each.paint(g, x, y, size);
                x += size * 2;
            }
            if (data.isStartGame()) {
                paintButton("START");
            } else {
                if (!data.isDiceRolled()) {
                    paintButton("ROLL");
                }
                Slot indicator = new SlotBG(1, data.player);

                indicator.paintStone(g, getWidth() / 2 - anchorD / 9 / 2 + gap, gap, getTriWid() * 3 / 5);
                g.setColor(Color.white);
                g.setFont(new Font("Avenir", Font.BOLD, getTriWid() * 3 / 5));
                g.drawString("'s turn", getWidth() / 2 - anchorD / 9 / 2 + gap + getTriWid() * 3 / 5, gap - 6 + getTriWid() * 3 / 5);
            }
        }
    }

    private void paintButton(String s) {
        g.setColor(Color.white);
        g.fillRect(getWidth() / 2 + anchorD / 9 / 4, gap, anchorD / 9 / 4 - gap, getTriWid() * 3/5);

        g.setColor(Color.black);
        g.setFont(new Font("Avenir", Font.BOLD, getTriWid()*3/7));

        g.drawString(s, getWidth() / 2 + anchorD / 9 / 4 + gap * 7 / 4, gap - 9 + getTriWid()*3/5);
    }
}
