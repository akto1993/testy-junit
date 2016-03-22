/**
 * Created by Greg on 22.03.2016.
 */
public interface IMyList {
    public void add(Warzywa warzywo);
    Warzywa get(int index);
    IMyList getAll();
    int size();
    boolean isEmpty();
}
