import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;


public class WarzywaTest {

    @Rule
            public EasyMockRule mocks = new EasyMockRule(this);
    @Mock
            IMyList mock;
    @TestSubject
            private WarzywaManager myApp = new WarzywaManager(mock);


    @Test
    public void addTest(){
        Warzywa w = new Warzywa("rzeczowa","rzecz",10.1,1992);

        mock.add(w);
        expectLastCall();
        expect(mock.size()).andReturn(1);
        expect(mock.getAll()).andReturn(mock);
        replay(mock);
        myApp.add(w);

        assertEquals(1,myApp.getAll().size());
        verify(mock);
    }


}