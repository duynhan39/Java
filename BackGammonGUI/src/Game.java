import java.util.ArrayList;
import java.util.Arrays;

public abstract class Game {
    protected String name;
    protected Board board;
    protected int player;
    protected boolean cont;
    protected int to, from;

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    Game() {
        player = 1;
        cont = true;
        to = -2;
        from = -2;
    }

    public Board getBoard() { return board; }

    public abstract int getState();

    public abstract void setState(int state);

    public abstract ArrayList<Dice> getDice();

    public int getPlayer() { return player; }

    public void changePlayer() {
        player = player % 2 + 1;
    }

    public abstract int pickFirstPlayer() throws InterruptedException;

    public abstract void rollAllDice() throws InterruptedException;

    public abstract ArrayList<Integer> getHoldList();
}
