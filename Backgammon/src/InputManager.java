import java.util.*;
import java.util.concurrent.TimeUnit;

public class InputManager {
    private Game data;
    private OpenMove openMove;
    private Map<Integer, Integer> availableMove;

    public void readInput(Game data) throws InterruptedException {
        this.data = data;
        openMove = new OpenMove(data.getDice());
        do {
            Integer from = 0, to = 0;
            do {
                availableMove = new HashMap<>();
                availableMove.put(0, 0);
                from = readSlot();

                System.out.print("Option(s): ");
                System.out.println(availableMove.keySet());

                to = readMove();
            } while (to == -1);

            data.getBoard().makeMove(from, to, data.getPlayer() == data.getBoard().getSlot(to).getPlayer());
            openMove.removeMove(availableMove.get(to));

        } while(openMove.getMoves().size() > 0);

        System.out.println("Turn ends.");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int readSlot() {
        Integer from;
        Scanner reader = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Pick a slot: ");
                from = reader.nextInt();
                from--;

                if (from < 0 || from > 23)
                    throw new InputMismatchException("Index out of range.");

                if (data.getBoard().getSlot(from).getPlayer() == 0)
                    throw new InputMismatchException("Cannot pick an empty slot.");

                if (data.getBoard().getSlot(from).getPlayer() != data.getPlayer())
                    throw new InputMismatchException("That is not your to pick!");

                moveList(from);

                if(availableMove.size() == 0)
                    throw new InputMismatchException("No available moves!");

                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! ");
                System.out.println(e.getMessage());
                reader = new Scanner(System.in);
            }
        }
        return from;
    }

    private void moveList(Integer from) {
        for(Integer eachMove:openMove.getMoves()) {
            int target = from - (data.getPlayer() * 2 - 3) * eachMove;
            if ( target >= 1 &&
                    target <= 24 &&
                    data.getBoard().getSlot(target).isOpen(data.getPlayer()) )
                availableMove.put(target + 1, eachMove);
        }
    }

    private int readMove() {
        Integer to;
        Scanner reader = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Where do you want to move it to? ");
                to = reader.nextInt();
                if( !availableMove.containsKey(to) )
                    throw new InputMismatchException("You cannot move it there!");
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! ");
                System.out.println(e.getMessage());
                reader = new Scanner(System.in);
            }
        }
        to--;
        return to;
    }
}
