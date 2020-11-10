package main.java;

import main.java.Stuffing.StuffingEnum;

public class Main {

    /**
     * Basic main runner class.
     *
     */

    public static void main(String[] args) {
        // Fill me in!

        Bear bear1 = new Bear(StuffingEnum.FOAM); // SER316 TASK 2 SPOTBUGS FIX
        Bear bear2 = new Bear(StuffingEnum.BASE); // SER316 TASK 2 SPOTBUGS FIX
        Bear bear3 = new Bear(StuffingEnum.DOWN); // SER316 TASK 2 SPOTBUGS FIX

        bear1.casing = new Casing(2); // SER316 TASK 2 SPOTBUGS FIX

        BearWorkshop workshop = new BearWorkshop("AZ");
        workshop.addBear(bear2);
        workshop.addBear(bear3);
        workshop.addBear(bear1);

        System.out.println("bear 1 price " + bear1.price);

        System.out.println("bear 2 price workshop.get " + workshop.getCost(bear2));
        System.out.println("bear 2 price " + bear2.price);

        System.out.println("bear 3 price workshop " + workshop.getCost(bear3));
        System.out.println("bear 3 price " + bear3.price);

        bear1.clothing.add(new Clothing());

        System.out.println("Bear with NO clothing object");
        System.out.println(bear1.price);

        System.out.println("Bear with one clothing object");
        System.out.println(workshop.getRawCost(bear1));

        bear1.clothing.add(new Clothing(5));
        System.out.println("Bear with two clothing object");
        bear1.clothing.add(new Clothing(5));
        bear1.clothing.add(new Clothing(5));
        bear1.clothing.add(new Clothing(5));
        bear1.clothing.add(new Clothing(4));
        bear1.clothing.add(new Clothing(3));
        bear1.clothing.add(new Clothing(2));
        bear1.clothing.add(new Clothing(2));
        System.out.println(workshop.calculateSavings());
    }
}
