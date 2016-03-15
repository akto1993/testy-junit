import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 15.03.2016.
 */
public class wegetableManager {
    List<Wegetable> wegetables = new ArrayList<Wegetable>();
    public void add(Wegetable wege) {
        wegetables.add(wege);
    }
    public List<Wegetable> getAll(){
        return wegetables;
    }
}