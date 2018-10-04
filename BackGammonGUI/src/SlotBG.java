import java.awt.*;
import java.util.Arrays;

public class SlotBG extends Slot {
    private int player;
    protected boolean isClicked;
    protected boolean isMovable;

    private Color salmon = new Color(236, 73, 93);

    @Override
    public boolean isClicked() {
        return isClicked;
    }

    @Override
    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    @Override
    public boolean isMovable() {
        return isMovable;
    }

    @Override
    public void setMovable(boolean movable) {
        isMovable = movable;
    }
    
    SlotBG(int init, int player){
        super(init);
        this.player = player;
    }
    
    SlotBG(){
        this(0, 0);
    }

    @Override
    public void add(int player, int num){
        if(this.player == player || this.player == 0) {
            super.add(num);
            this.player = player;
        }
    }

    @Override
    public boolean isOpen(int player) { return(this.player == 0 || this.player == player || this.stone == 1); }

    @Override
    public void remove() {
        if(this.stone > 0)
            this.stone--;

        if(this.stone == 0)
            this.player = 0;
    }

    @Override
    public void setSize(int num, int player) {
        this.stone = num;
        this.player = player;
    }

    @Override
    public void draw() {
        char stone = '*';
        if(this.player == 1)
            stone = '0';
        char[] arr = new char[this.stone];
        Arrays.fill(arr, stone);
        System.out.print(arr);
    }

    @Override
    public void draw(int line, int slotSize) {}

    @Override
    public int getPlayer() { return player; }

    @Override
    public void paintStone(Graphics g, int x, int y, int size) {
        g.setColor(Color.black);
        g.drawOval(x, y, size, size);

        if(player == 1)
            g.setColor(salmon);
        else
            g.setColor(Color.white);

        g.fillOval(x, y, size, size);

        g.setColor(Color.black);
        g.drawOval(x + size/10, y + size/10, size*4/5, size*4/5);
    }

    @Override
    public void paint(Graphics g, BoardView boardView, int orient, int x) {
        int space, size;
        space = size = boardView.getTriWid()*3/5;

        int y = boardView.getHeight() / 2
                        - orient * (boardView.getAnchorD() / 15 / 2
                        - boardView.getGap())
                        + boardView.getNameHeight();

        if(stone * size > boardView.getTriHei())
            space = (boardView.getTriHei() - size) / ( stone - 1 );

        if(orient == -1)
            y += orient * size;

        for(int i = 0; i < stone; i++) {
            paintStone(g, x, y, size);
            y += orient * space;
        }
    }
}
