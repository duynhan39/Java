import java.awt.event.MouseEvent;

public abstract class InputManager{
    protected Game data;

    public InputManager(GameBG game) {data = game;}

    public abstract void readInput(GameBG data, MouseEvent e, BoardView view) throws InterruptedException;
}
