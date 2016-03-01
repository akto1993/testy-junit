package yolo;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Created by Greg on 01.03.2016.
 */
public class CalculatorTest {
    Calculator calc = new Calculator();
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
    }
}