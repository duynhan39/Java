import java.awt.*;
import java.util.ArrayList;

/**
 * Created by duynhan on 4/2/18.
 */
public abstract class Board {
    protected ArrayList<Slot> slots;
    protected int boardSize;
    protected int gapLine;

    public int getSize() { return boardSize + gapLine; }

    public abstract void setGraphics(Graphics g);

    public abstract void setBoardView(BoardView b);

    public Slot getSlot(int index){
        return slots.get(index);
    }

    public abstract void paintMiddle();

    public abstract void makeMove(int from, int to, boolean same);

    public abstract ArrayList<Integer> generateHoldList(int Player);

    public abstract void paint();

    public abstract void paintSlotHolder(int x0);
}
