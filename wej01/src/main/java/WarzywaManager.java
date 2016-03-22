import java.util.ArrayList;
import java.util.List;

public class WarzywaManager implements IwarzywaManager{

    private List<Warzywa> warzywa = new ArrayList();

    public void add(Warzywa l) {
        // TODO Auto-generated method stub
        this.warzywa.add(l);

    }

    public List<Warzywa> getAll() {
        // TODO Auto-generated method stub
        return this.warzywa;

    }
}