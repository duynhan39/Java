import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by duynhan on 4/2/18.
 */
public class DiceValues {

    private Map< Integer, ArrayList<Integer> > values;
    private ArrayList<Dice> availableDice;

    public ArrayList<Dice> getAvailableDice() { return availableDice; }

    public DiceValues(ArrayList<Dice> dice) {
        availableDice = new ArrayList<>();
        for(Dice eachDice:dice)
            availableDice.add(eachDice);
        values = new HashMap<>();

        if(availableDice.get(0).getNum() == availableDice.get(1).getNum()) {

            availableDice.add(new Dice(dice.get(0)));
            availableDice.add(new Dice(dice.get(0)));
        }
        generateMove();
    }

    private void generateMove() {

        values = new HashMap<>();

        if(availableDice.size() == 0)
            return;

        ArrayList<Integer> diceIncluded = new ArrayList<>();
        diceIncluded.add(0);
        values.put(availableDice.get(0).getNum(), new ArrayList<>(diceIncluded));

        if(availableDice.size() > 1 && availableDice.get(0).getNum() != availableDice.get(1).getNum()) {

            diceIncluded.remove(0);
            diceIncluded.add(1);
            values.put(availableDice.get(1).getNum(), new ArrayList<>(diceIncluded));

            diceIncluded.remove(0);
            diceIncluded.add(0);
            diceIncluded.add(1);
            values.put(availableDice.get(0).getNum() + availableDice.get(1).getNum(), new ArrayList<>(diceIncluded));

        } else {
            int sum = availableDice.get(0).getNum();
            for(int i = 2; i <= availableDice.size(); i++) {

                diceIncluded.add(i-1);
                sum += availableDice.get(0).getNum();

                values.put(sum, new ArrayList<>(diceIncluded));
            }
        }
    }

    public Set<Integer> getValues() { return values.keySet(); }

    public void removeMove(Integer move) {

        for(int index=this.values.get(move).size() - 1; index >= 0; index--) {
            availableDice.remove( (int)this.values.get(move).get(index) );
        }
        generateMove();
    }
}
