import java.awt.*;

/**
 * Created by duynhan on 4/2/18.
 */
public abstract class Slot {
    protected int stone;

    public Slot() {
        stone = 0;
    }

    public Slot(int init) {
        stone = init;
    }

    public int getNum() { return stone; }

    public void add(int extra) { stone+=extra; }

    public abstract boolean isClicked();

    public abstract void setClicked(boolean clicked);

    public abstract boolean isMovable();

    public abstract void setMovable(boolean movable);

    public abstract void add(int player, int num);

    public abstract boolean isOpen(int player);

    public abstract void remove();

    public abstract void setSize(int num, int player);

    public abstract void draw();

    public abstract void draw(int line, int slotSize);

    public abstract int getPlayer();

    public abstract void paintStone(Graphics g, int x, int y, int size);

    public abstract void paint(Graphics g, BoardView boardView, int orient, int x);
}
