import java.awt.*;
import java.util.ArrayList;

public class BoardBG extends Board{
    private ArrayList<Integer> holdList;

    private Color darkorange = new Color(207, 112, 40);
    private Color stoneeyellow = new Color(212, 197, 161);

    private Graphics g;

    private BoardView b;

    public BoardBG() {
        boardSize = 36;
        gapLine = 10;

        slots = new ArrayList<>();
        for(int slotNum = 0; slotNum < 26; slotNum++) {
            slots.add(new SlotBG());
        }
        slots.get(1).setSize(2, 1);
        slots.get(24).setSize(2, 2);

        slots.get(6).setSize(5, 2);
        slots.get(19).setSize(5, 1);

        slots.get(8).setSize(3, 2);
        slots.get(17).setSize(3, 1);

        slots.get(12).setSize(5, 1);
        slots.get(13).setSize(5, 2);

        slots.get(0).setSize(0, 1);
        slots.get(25).setSize(0, 2);

//        slots.get(6).setSize(1, 2);
//        slots.get(19).setSize(1, 1);

    }

    @Override
    public void setGraphics(Graphics g) {this.g = g;}

    @Override
    public void setBoardView(BoardView b) {this.b = b;}

    @Override
    public void paintMiddle() {
        getSlot(0).paint(g, b, -1, b.getWidth()/2 - b.getTriWid()*3/10);
        getSlot(25).paint(g, b, 1, b.getWidth()/2 - b.getTriWid()*3/10);
    }

    @Override
    public void makeMove(int from, int to, boolean same) {
        if( slots.get(to).getNum() == 1 && !same ) {
            slots.get(to).remove();
            slots.get( ( 2 - slots.get(from).getPlayer() ) * 25 ).add( slots.get(from).getPlayer() % 2 + 1, 1);
        }

        slots.get(to).add( slots.get(from).getPlayer(), 1 );
        slots.get(from).remove();
    }

    @Override
    public ArrayList<Integer> generateHoldList(int Player) {
        holdList = new ArrayList<>();
        if( slots.get( ( Player -  1) * 25 ).getNum() != 0 ) {
            holdList.add( ( Player -  1) * 25 );
        } else {
            for(int eachSlot = 1; eachSlot <= 24; eachSlot++) {
                if( slots.get(eachSlot).getPlayer() == Player ) {
                    holdList.add(eachSlot);
                }
            }
        }
        return holdList;
    }

    @Override
    public void paint() {

        int x0 = b.getWidth() / 2 - b.getAnchorD() / 9 / 2 + b.getGap();
        paintSlotHolder(x0);

        x0 = b.getWidth() / 2 + b.getGap();
        paintSlotHolder(x0);

        paintStones();

        paintMiddle();
    }

    private void paintStones() {
        int x0 = b.getWidth() / 2 - b.getAnchorD() / 9 / 2 + b.getGap();
        int side = 1;
        paintSide(x0, side);

        x0 = b.getWidth() / 2 + b.getGap();
        side = 2;
        paintSide(x0, side);
    }

    private void paintSide(int x0, int side) {
        int x;

        for (int i = 0; i < 6; i++) {
            x = i * b.getTriWid() + x0;

            paintSlot(i, side, -1, x);
            paintSlot(i, side, 1, x);
        }
    }

    private void paintSlot(int index, int side, int orient, int x) {
        Slot temSlot = getSlot(25 * (orient + 1) / 2 - orient * ( 6 - index + 6 *(2-side) ) );

        if(temSlot.isClicked() || temSlot.isMovable()) {

            g.setColor(Color.green);
            if(temSlot.isClicked())
                g.setColor(Color.yellow);

            paintTriangle(
                    b,
                    x,
                    b.getHeight() / 2
                    - orient * (b.getAnchorD() / 15 / 2
                    - b.getGap())
                    + b.getNameHeight(),
                    orient,
                    false
            );
        }
        int size = b.getTriWid()*3/5;
        temSlot.paint(g, b, orient, x + b.getTriWid()/2 - size/2);
    }

    @Override
    public void paintSlotHolder(int x0) {
        int x, y;
        int orient;
        g.setColor(darkorange);

        for (int i = 0; i < 6; i++) {

            x = i * b.getTriWid() + x0;
            orient = -1;
            y = b.getHeight() / 2 + (b.getAnchorD() / 15 / 2 - b.getGap()) + b.getNameHeight();
            paintTriangle(b, x, y, orient, true);

            g.setColor(changeColor());

            orient = 1;
            y = b.getHeight() / 2 - (b.getAnchorD() / 15 / 2 - b.getGap()) + b.getNameHeight();
            paintTriangle(b, x, y, orient, true);
        }
    }

    private void paintTriangle(BoardView boardView, int x, int y, int orient, boolean filled) {
        int xpoints[] = {x, x + boardView.getTriWid() / 2, x + boardView.getTriWid()};
        int ypoints[] = {y, y + boardView.getTriHei() * orient, y};
        int npoints = 3;

        if(filled)
            g.fillPolygon(xpoints, ypoints, npoints);
        else
            g.drawPolygon(xpoints, ypoints, npoints);
    }

    private Color changeColor() {
        if( g.getColor() == darkorange)
            return stoneeyellow;
        return darkorange;
    }
}
