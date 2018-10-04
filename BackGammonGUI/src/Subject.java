/**
 * Created by duynhan on 4/26/18.
 */
public interface Subject {
    public void register(Observer o);
    public void notifyObserver();
}
