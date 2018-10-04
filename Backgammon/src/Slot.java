import sun.misc.InvalidJarIndexException;

import java.util.Arrays;

public class Slot {
    private int stone;
    private int player;

    Slot(int init, int player){
        //super(init);
        this.player = player;
    }

    Slot(){
        this(0, 0);
    }

    public void add(int player){
        //super.add(1);
        this.player = player;
    }

    public void add(){
        //super.add(1);
    }

    public boolean isOpen(int player) { return(this.player == 0 || this.player == player || this.stone == 1); }

    public int getNum() {
        return this.stone;
    }

    public boolean remove() {
        if(this.stone > 0) {
            this.stone--;
            if(this.stone == 0)
                this.player = 0;
        } else {
            throw new InvalidJarIndexException() ;
        }
        return true;
    }

    public void setSize(int num, int player) {
        this.stone = num;
        this.player = player;
    }

    public void drawSlot() {
        char stone = '*';
        if(this.player == 1)
            stone = '0';
        char[] arr = new char[this.stone];
        Arrays.fill(arr, stone);
        System.out.print(arr);
    }

    public int getPlayer() { return player; }
}
