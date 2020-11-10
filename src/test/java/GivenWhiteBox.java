import main.java.*;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

public class GivenWhiteBox {
    BearWorkshop oneBear;

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        BearWorkshop temp = new BearWorkshop(name);
        return temp;
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        Bear temp = new Bear();
        oneBear.addBear(temp);
        Double ans = oneBear.checkout();

        assertEquals(30 * 1.07, ans, 0.005);
    }

    @Test
    public void addingCustomer() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        Customer custard = new Customer("AZ");
        Customer custy = new Customer(16, "AZ", custard);
        Bear temp = new Bear();
        oneBear.addBear(temp);
        Double ans = oneBear.checkout();

        assertEquals(30 * 1.07, ans, 0.005);
    }

    // Testing node coverage {All nodes}
    @Test
    public void testingNodeCoverage() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE); // no clothes, no accessories, should be 30 and free.
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE); // will have 2 clothes no discount
        customBear2.clothing.add(new Clothing(5, "TestClothing"));
        customBear2.clothing.add(new Clothing(5, "TestClothing2"));

        Bear customBear3 = new Bear(Stuffing.stuffing.BASE); // will have 7 clothes and 5 noise makers - 2 free clothes
                                                             // and 10 percent off
        customBear3.clothing.add(new Clothing(4, "TestClothing"));
        customBear3.clothing.add(new Clothing(3, "TestClothing2"));
        customBear3.clothing.add(new Clothing(5, "TestClothing3"));
        customBear3.clothing.add(new Clothing(5, "TestClothing4"));
        customBear3.clothing.add(new Clothing(5, "TestClothing5"));
        customBear3.clothing.add(new Clothing(5, "TestClothing6"));
        customBear3.clothing.add(new Clothing(5, "TestClothing7"));
        customBear3.noisemakers.add(new NoiseMaker()); // 10 bucks
        customBear3.noisemakers.add(new NoiseMaker("Label1", "Recording1", NoiseMaker.Location.LEFT_FOOT)); // 5
        customBear3.noisemakers.add(new NoiseMaker("Label2", "Recording2", NoiseMaker.Location.LEFT_HAND)); // 5
        customBear3.noisemakers.add(new NoiseMaker("Label3", "Recording3", NoiseMaker.Location.RIGHT_FOOT)); // 5
        customBear3.noisemakers.add(new NoiseMaker("Label4", "Recording4", NoiseMaker.Location.RIGHT_HAND)); // 5
        Bear customBear4 = new Bear(Stuffing.stuffing.BASE); // Some embroidery to check if that works full price no
                                                             // discount
        customBear4.ink = new Embroidery("TestInk");

        bears.addBear(customBear);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        bears.addBear(customBear4);

        // double bear = bears.getCost(customBear); //free = 30 package deal get cost
        // does not work for that
        double bear2 = bears.getCost(customBear2); // 40 = 30 + 5 + 5
        double bear3 = bears.getCost(customBear3);// 76.5 = 30+5+5+5+5+5+10+5+5+5+5 - (.1)(30+5+5+5+5+5+10+5+5+5+5)
        double bear4 = bears.getCost(customBear4);// 37
        // System.out.println(bear);

        double ans = bear2 + bear3 + bear4;
        double raw = bears.getRawCost(customBear) + bears.getRawCost(customBear2) + bears.getRawCost(customBear3)
                + bears.getRawCost(customBear4);
        double savings = bears.calculateSavings();
        double expectedAns = raw - savings;

        assertEquals(bear2, 40, 0.05);
        assertEquals(bear3, 76.5, 0.05);
        assertEquals(bear4, 37, 0.05);
    }

    @Test
    public void testTaxNY() {
        // One Student
        oneBear = new BearWorkshop("NY");
        Bear temp = new Bear();
        oneBear.addBear(temp);
        Double ans = oneBear.checkout();

        assertEquals(30 * 1.09, ans, 0.005);
    }

    @Test
    public void testTaxVA() {
        // One Student
        oneBear = new BearWorkshop("VA");
        Bear temp = new Bear();
        oneBear.addBear(temp);
        Double ans = oneBear.checkout();

        assertEquals(30 * 1.05, ans, 0.005);
    }

    @Test
    public void testTaxDC() {
        // One Student
        oneBear = new BearWorkshop("DC");
        Bear temp = new Bear();
        oneBear.addBear(temp);
        Double ans = oneBear.checkout();

        assertEquals(30 * 1.105, ans, 0.005);
    }

    @Test
    public void testTaxCA() {
        // One Student
        oneBear = new BearWorkshop("CA");
        Bear temp = new Bear();
        oneBear.addBear(temp);
        Double ans = oneBear.checkout();

        assertEquals(30 * 1.1, ans, 0.005);
    }

}
