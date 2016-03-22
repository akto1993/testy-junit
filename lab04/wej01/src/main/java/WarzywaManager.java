import java.util.ArrayList;
import java.util.List;

public class WarzywaManager implements IwarzywaManager{

    //private List<Warzywa> warzywa = new ArrayList();
    private IMyList warzywa;

    public WarzywaManager (IMyList warzywa){
        super();
        this.warzywa = warzywa;
    }

    public void add(Warzywa l) {
        this.warzywa.add(l);

    }

    public IMyList getAll() {
        return warzywa.getAll();
    }
}