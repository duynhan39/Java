import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duynhan on 4/1/18.
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
//        printGameName();
//        new Game();

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Integer sum = 0;
        map.put(sum, new ArrayList<>(arr));
        //sum--;
        System.out.println(arr);
        System.out.println(map.get(sum));
        arr.add(5);
        System.out.println(map.get(sum));
    }

    private static void printGameName() {
        String gameName = "BACKGMAMMON";

        char[] spaces = new char[ (34 - gameName.length())/2 ];
        Arrays.fill(spaces, ' ');
        System.out.print(spaces);
        System.out.print(gameName);
        System.out.println(spaces);

        System.out.println("Player 1: 0");
        System.out.println("Player 2: *");

        System.out.println("FlagIN");
    }
}
