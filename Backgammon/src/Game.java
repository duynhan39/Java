import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
    private Board board;
    private ArrayList<Dice> dice = new ArrayList<>();
    private int player;

    public Game() throws InterruptedException {
        dice.add(new Dice());
        dice.add(new Dice());

        board = new Board();
        player = 0;
        board.draw();

        Run();
    }

    private void Run() throws InterruptedException {
        player = pickFirstPlayer();

        while(true) {
            System.out.print("Player " + player + "'s turn. ");
            rollAllDice();
            System.out.println("Player " + player + " gets " + dice.get(0).getNum() + " and " + dice.get(1).getNum());

            InputManager input = new InputManager();

            input.readInput(this);
            board.draw();

            changePlayer();
        }
    }

    public Board getBoard() { return board; }

//    public Dice getDice(int num) {
//        try {
//            return dice.get(num - 1);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Enter valid input (1-" + dice.size() + ")\n");
//        }
//        return dice.get(0);
//    }

    public ArrayList<Dice> getDice() { return dice; }

    public int getPlayer() {
        return player;
    }

    private void changePlayer() {
        player = player % 2 + 1;
    }

    private int pickFirstPlayer() throws InterruptedException {

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
            System.out.println("\nPlayer 1 plays first!");
            return 1;
        }
        System.out.println("\nPlayer 2 plays first!\n");
        return 2;
    }

    private void rollAllDice() throws InterruptedException {
        System.out.println("Throwing the dice...");
        try {
            dice.get(0).Roll();
            dice.get(1).Roll();
        } catch (InterruptedException e) {
            System.out.println("Something wrong happens in Game.java");
        }
    }

}
