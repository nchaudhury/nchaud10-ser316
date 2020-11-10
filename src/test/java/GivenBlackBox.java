import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import main.java.BearWorkshop;

import static org.junit.Assert.*;

/***
 * This class provides a framework to implement black box tests for various
 * implementations of the BearWorkshop Class. The BearWorkshop is having a
 * blowout sale and is offering the following savings.
 *
 * Bears are Buy 2 bears, get 1 free. It is 10% off the cost of a bear when a
 * single bear has 10 or more accessories (Note that embroidery, stuffing, and
 * the material used for the bear casing is not considered an accessory).
 * Additionally, clothes are buy 2, get one free on each bear.
 */
@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<BearWorkshop> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
        this.classUnderTest = (Class<BearWorkshop>) classUnderTest;
    }

    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = { { BearWorkshop1.class }, { BearWorkshop2.class }, { BearWorkshop3.class },
                { BearWorkshop4.class }, { BearWorkshop5.class }

        };
        return Arrays.asList(classes);
    }

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        Constructor<BearWorkshop> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }

    BearWorkshop oneBear;
    Double oneBearExpected;

    BearWorkshop threeBears;
    Double threeBearsExpected;

    BearWorkshop twoBears;
    Double twoBearsExpected;

    @Before
    public void setUp() throws Exception {

        // One Bear base stuffing, no saving expected
        oneBear = createBearWorkshop("NY");
        oneBear.addBear(new Bear(Stuffing.stuffing.BASE));
        oneBearExpected = 0.00; // no savings

        // Three Bears expected to not pay for cheapest one
        threeBears = createBearWorkshop("AZ");
        threeBears.addBear(new Bear(Stuffing.stuffing.BASE));
        threeBears.addBear(new Bear(Stuffing.stuffing.DOWN));
        threeBears.addBear(new Bear(Stuffing.stuffing.FOAM));
        threeBearsExpected = 30.00;
        twoBears = createBearWorkshop("CA");
        twoBears.addBear(new Bear(Stuffing.stuffing.BASE));
        twoBears.addBear(new Bear());
        twoBearsExpected = 0.0;
    }

    @After
    public void tearDown() throws Exception {
    }

    // sample test

    /**
     * Test examines a BearFactory with 1 simple bear in it. The bear costs $30 and
     * there are no savings.
     */
    @Test
    public void oneBearNoSavings() {
        Double ans = oneBear.calculateSavings();
        assertEquals(oneBearExpected, ans);
    }

    @Test
    public void twoBearNoSavings() {
        Double ans = twoBears.calculateSavings();
        assertEquals(twoBearsExpected, ans);
    }

    // sample test
    @Test
    public void threeBearsSaveOnCheapest() {
        Double ans = threeBears.calculateSavings();
        assertEquals(threeBearsExpected, ans);
    }

    // sample test

    @Test
    public void oneBearTest3clothingsExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));

        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test
    public void oneBearTestoneClothing() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(5, "Hat"));
        Double bearsExpected = 0.0; // no saving
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test
    public void oneBearTesttwoClothing() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(5, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        Double bearsExpected = 0.0; // no savings
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test
    public void oneBearManyAccessories() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.clothing.add(new Clothing(5, "Hat2"));
        customBear.clothing.add(new Clothing(5, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(3, "Jacket"));
        customBear.clothing.add(new Clothing(2, "Glove"));
        customBear.clothing.add(new Clothing(2, "OtherGlove"));
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker("Label1", "Recording1", NoiseMaker.Location.LEFT_FOOT));
        customBear.noisemakers.add(new NoiseMaker("Label2", "Recording2", NoiseMaker.Location.LEFT_HAND));
        customBear.noisemakers.add(new NoiseMaker("Label3", "Recording3", NoiseMaker.Location.RIGHT_FOOT));
        customBear.noisemakers.add(new NoiseMaker("Label4", "Recording4", NoiseMaker.Location.RIGHT_HAND));
        Double rawCost = bears.getRawCost(customBear);
        Double bearsExpected = 4 + 0.1 * (rawCost - 4); // 4 + 0.1(raw cost)
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test // Boundary Test
    public void oneBearNOembroidery() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.ink = new Embroidery("");
        bears.addBear(customBear);

        Double bearsExpected = 30.0; // no savings
        Double ans = bears.getCost(customBear);
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test // Boundary Test
    public void oneBearsmallembroidery() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.ink = new Embroidery("T");
        bears.addBear(customBear);

        Double bearsExpected = 31.0; // no savings
        Double ans = bears.getCost(customBear);
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test // Boundary test
    public void oneBearmediumembroidery() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.ink = new Embroidery("TESTSTRINGMEDIUM");
        bears.addBear(customBear);

        Double bearsExpected = 46.0; // no savings
        Double ans = bears.getCost(customBear);
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test // Boundary test
    public void oneBearLargeEmbroidery() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.ink = new Embroidery("TESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGE");
        bears.addBear(customBear);

        Double bearsExpected = 75.0 + 30.0; // no savings
        Double ans = bears.getCost(customBear);
        Double ans2 = bears.getRawCost(customBear); // Class5 is not adding cost for embroidery
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test
    public void explosionTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        customBear.ink = new Embroidery("TESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGE");
        customBear.clothing.add(new Clothing(5, "Hat2"));
        customBear.clothing.add(new Clothing(5, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(3, "Jacket"));
        customBear.clothing.add(new Clothing(2, "Glove"));
        customBear.clothing.add(new Clothing(2, "OtherGlove"));
        customBear.clothing.add(new Clothing(2, "OtherGlove2"));
        customBear.noisemakers.add(new NoiseMaker("Label1", "Recording1", NoiseMaker.Location.LEFT_FOOT));
        customBear.noisemakers.add(new NoiseMaker("Label2", "Recording2", NoiseMaker.Location.LEFT_HAND));
        customBear.noisemakers.add(new NoiseMaker("Label3", "Recording3", NoiseMaker.Location.RIGHT_FOOT));
        customBear.noisemakers.add(new NoiseMaker("Label4", "Recording4", NoiseMaker.Location.RIGHT_HAND));

        Double bearsExpected = 4.0; // no savings
        Double ans = bears.calculateSavings();
        Double ans2 = bears.getRawCost(customBear);
        Double ans3 = bears.getCost(customBear);

        // System.out.println(ans2 + " raw sep cost" + ans3);
        assertEquals(bearsExpected, ans, 0.005);
    }

}
