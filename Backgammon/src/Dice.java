import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by duynhan on 4/1/18.
 */
public class Dice {
    int num;

    Dice() {}

    Dice(Dice dice) {
        this.num = dice.num;
    }

    public void Roll() throws InterruptedException {
        Random rand = new Random(System.currentTimeMillis());
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));

        rand = new Random(System.currentTimeMillis());
        num = rand.nextInt(6) + 1;
    }

    public int getNum(){
        return num;
    }
}
