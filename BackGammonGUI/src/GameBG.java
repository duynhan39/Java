import java.util.ArrayList;

public class GameBG  extends Game implements Subject{
    private ArrayList<Dice> dice = new ArrayList<>();
    private ArrayList<Observer> observers;
    private int state;
    private boolean startGame;
    private boolean endGame;
    private boolean diceRolled;
    private boolean isOver;

    public GameBG(){
        super();
        dice.add(new Dice());
        dice.add(new Dice());
        board = new BoardBG();
        name = "BACKGMAMMON";
        observers = new ArrayList<>();
        state = 1;
        startGame = true;
        endGame = false;
        diceRolled = false;
        isOver = false;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public boolean isDiceRolled() { return diceRolled; }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public void setDiceRolled(boolean diceRolled) { this.diceRolled = diceRolled; }

    @Override
    public ArrayList<Dice> getDice() { return dice; }

    @Override
    public int pickFirstPlayer() throws InterruptedException {

        boolean firstTry = true;
        do {
            if(firstTry) {
                firstTry = false;
            } else
                System.out.print("Same numbers. ");

            rollAllDice();

            for(int i = 1; i <= 2; i++) {
                System.out.println("Player " + i + " get " + dice.get(i-1).getNum());
            }

        } while(dice.get(0).getNum() == dice.get(1).getNum());

        if(dice.get(0).getNum() > dice.get(1).getNum()) {
            System.out.println("\nPlayer 1 plays first!\n");
            return 1;
        }
        System.out.println("\nPlayer 2 plays first!\n");
        return 2;
    }

    @Override
    public void rollAllDice() throws InterruptedException {
        System.out.println("Throwing the dice...");
        try {
            dice.get(0).Roll();
            dice.get(1).Roll();
        } catch (InterruptedException e) {
            System.out.println("Something wrong happens in Game.java");
        }
    }

    @Override
    public ArrayList<Integer> getHoldList() { return board.generateHoldList( player ); }

    public void setUp(InputManagerBG input) {
        try {
            rollAllDice();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Player " + player + " gets " + dice.get(0).getNum() + " and " + dice.get(1).getNum());

        input.diceValues = new DiceValues( dice );
        boolean noAvailableMove = true;

        for(Integer slotNum:board.generateHoldList( player ) ) {
            input.generateMoveList(slotNum);
            if(input.availableMove.size() != 1) {
                noAvailableMove = false;
                break;
            }
        }
        if(noAvailableMove) {
            System.out.println("No available moves! Good luck next time!");
            return;
        }
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer each : observers)
            each.update();
    }
}
