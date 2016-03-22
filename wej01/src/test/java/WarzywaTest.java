import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class WarzywaTest {

    WarzywaManager menagerWarzyw = new WarzywaManager();

    Warzywa warzywko1 = new Warzywa("cebula","czerwona",10,2016);
    Warzywa warzywko2 = new Warzywa("czosnek","zwyczajny",15,2015);

    @Test
    public void checkList(){
        assertTrue(menagerWarzyw.getAll().isEmpty());
    }

    @Test
    public void addToList(){
        menagerWarzyw.add(warzywko1);
        List<Warzywa> warzywa = menagerWarzyw.getAll();
        assertEquals(warzywa.size(),1);
    }


}