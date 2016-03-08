import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Greg on 01.03.2016.
 */
public class CalculatorTest {
    Calculator calc = new Calculator();
    @Test
    public void testAdd() throws Exception {
        assertEquals(5, calc.add(2,3));
        assertEquals(3, calc.add(-2,5));
        assertEquals(4, calc.add(6,-2));
        assertEquals(-8, calc.add(-6,-2));
        assertEquals(2, calc.add(0,2));
        assertEquals(2, calc.add(2,0));
        assertEquals(0, calc.add(0,0));
        assertNotNull(calc.add(0,0));
    }

    @Test
    public void testSub() throws Exception {
        assertEquals(-1, calc.sub(2,3));
        assertEquals(-7, calc.sub(-2,5));
        assertEquals(8, calc.sub(6,-2));
        assertEquals(-4, calc.sub(-6,-2));
        assertEquals(-2, calc.sub(0,2));
        assertEquals(2, calc.sub(2,0));
        assertEquals(0, calc.sub(0,0));
        assertNotNull(calc.sub(0,0));
    }

    @Test
    public void testMulti() throws Exception {
        assertEquals(6, calc.multi(2,3));
        assertEquals(-10, calc.multi(-2,5));
        assertEquals(-12, calc.multi(6,-2));
        assertEquals(12, calc.multi(-6,-2));
        assertEquals(0, calc.multi(0,2));
        assertEquals(0, calc.multi(2,0));
        assertEquals(0, calc.multi(0,0));
        assertNotNull(calc.multi(0,0));
    }

    @Test
    public void testDiv() throws Exception {
        assertEquals(2, calc.div(6,3));
        assertEquals(-2, calc.div(-5,2));
        assertEquals(-3, calc.div(6,-2));
        assertEquals(3, calc.div(-6,-2));
        assertEquals(0, calc.div(0,2));
        assertNotNull(calc.div(1,1));
    }

    @Test
    public void testGreater() throws Exception {
        assertFalse(calc.greater(2,5));
        assertTrue(calc.greater(3,2));
    }
    /*Calculator calc = new Calculator();
    @Before
    public void setUp(){
        System.out.println("Before ");
    }

    @After
    public void tearDown(){
        System.out.println("After ");
    }
    @BeforeClass
    public static void globalSetUp(){
        System.out.println("BeforeGlobal ");
    }
    @AfterClass
    public static void globatTtearDown(){
        System.out.println("AfterGlobal ");
    }
    @Test
    public void testAdd() throws Exception {
        assertEquals(5, calc.add(2,3));
        System.out.println("Adding " + calc);
    }

    @Ignore
    @Test
    public void testSubstr() throws Exception {
        assertEquals(6, calc.add(3,3));
        System.out.println("Substr " + calc);
    }*/
}