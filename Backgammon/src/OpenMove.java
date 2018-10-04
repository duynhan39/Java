import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by duynhan on 4/2/18.
 */
public class OpenMove {

    private Map<Integer, ArrayList<Dice>> moves;
    private ArrayList<Dice> availableDice;

    public OpenMove(ArrayList<Dice> dice) {
        availableDice = new ArrayList<>();
        for(Dice eachDice:dice)
            availableDice.add(eachDice);
        moves = new HashMap<>();

        if(availableDice.get(0).getNum() == availableDice.get(1).getNum()) {

            availableDice.add(new Dice(dice.get(0)));
            availableDice.add(new Dice(dice.get(0)));
        }
        generateMove();
    }

    private void generateMove() {

        Map<Integer, ArrayList<Dice>> moves = new HashMap<>();

        if(availableDice.size() == 0)
            return;

        ArrayList<Dice> diceIncluded = new ArrayList<>();
        diceIncluded.add(availableDice.get(0));
        moves.put(availableDice.get(0).getNum(), diceIncluded);

        if(availableDice.size() > 1 && availableDice.get(0).getNum() != availableDice.get(1).getNum()) {

            diceIncluded = new ArrayList<>();
            diceIncluded.add(availableDice.get(1));
            moves.put(availableDice.get(1).getNum(), diceIncluded);

            diceIncluded = new ArrayList<>();
            diceIncluded.add(availableDice.get(0));
            diceIncluded.add(availableDice.get(1));
            moves.put(availableDice.get(0).getNum() + availableDice.get(1).getNum(), diceIncluded);

        } else {
            for(int i=2; i<=availableDice.size(); i++) {
                diceIncluded = new ArrayList<>();
                Integer sum = 0;
                for(int j=0; j<i; j++) {
                    diceIncluded.add(availableDice.get(j));
                    sum += availableDice.get(i).getNum();
                }
                moves.put(sum, diceIncluded);
            }
        }
        this.moves = moves;
    }

    public Set<Integer> getMoves() { return moves.keySet(); }

    public void removeMove(Integer move) {
        for(Dice eachDice:this.moves.get(move)) {
            availableDice.remove(availableDice.indexOf(eachDice));
        }
        generateMove();
    }
}
