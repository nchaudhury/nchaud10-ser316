import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.Bear;
import main.java.BearWorkshop;
import main.java.Clothing;
import main.java.Embroidery;
import main.java.NoiseMaker;
import main.java.Stuffing;
import main.java.Stuffing.StuffingEnum;

public class calculateSavingsTest {

    BearWorkshop oneBear;
    Double oneBearExpected;

    BearWorkshop threeBears;
    Double threeBearsExpected;

    BearWorkshop twoBears;
    Double twoBearsExpected;

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        BearWorkshop temp = new BearWorkshop(name);
        return temp;
    }

    @Before
    public void setUp() throws Exception {

        // One Bear base stuffing, no saving expected
        oneBear = createBearWorkshop("NY");
        oneBear.addBear(new Bear(Stuffing.StuffingEnum.BASE));
        oneBearExpected = 0.00; // no savings

        // Three Bears expected to not pay for cheapest one
        threeBears = createBearWorkshop("AZ");
        threeBears.addBear(new Bear(Stuffing.StuffingEnum.BASE));
        threeBears.addBear(new Bear(Stuffing.StuffingEnum.DOWN));
        threeBears.addBear(new Bear(Stuffing.StuffingEnum.FOAM));
        threeBearsExpected = 30.00;
        twoBears = createBearWorkshop("CA");
        twoBears.addBear(new Bear(Stuffing.StuffingEnum.BASE));
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
        oneBear.addBear(new Bear());
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
        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4));
        customBear.clothing.add(new Clothing(4));
        customBear.clothing.add(new Clothing(4));

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
        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(5));
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
        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(5));
        customBear.clothing.add(new Clothing(5));
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

        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
        customBear.clothing.add(new Clothing(3));
        customBear.clothing.add(new Clothing(2));
        customBear.clothing.add(new Clothing(1));

        customBear.clothing.add(new Clothing(4));
        customBear.clothing.add(new Clothing(5));
        customBear.clothing.add(new Clothing(5));
        customBear.clothing.add(new Clothing(5));
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker(NoiseMaker.Location.LEFT_FOOT));
        customBear.noisemakers.add(new NoiseMaker(NoiseMaker.Location.LEFT_HAND));
        customBear.noisemakers.add(new NoiseMaker(NoiseMaker.Location.RIGHT_FOOT));
        customBear.noisemakers.add(new NoiseMaker(NoiseMaker.Location.RIGHT_HAND));
        bears.addBear(customBear);
        double cost = bears.getCost(customBear);
        double bearsExpected = 17.5;
        double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test // Boundary Test
    public void oneBearNOembroidery() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
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
        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
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
        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
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
        Bear customBear = new Bear(Stuffing.StuffingEnum.BASE);
        customBear.ink = new Embroidery("TESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGETESTSTRINGLARGE");
        bears.addBear(customBear);

        Double bearsExpected = 75.0 + 30.0; // no savings
        Double ans = bears.getCost(customBear);
        Double ans2 = bears.getRawCost(customBear); // Class5 is not adding cost for embroidery
        assertEquals(bearsExpected, ans, 0.005);
    }

}
