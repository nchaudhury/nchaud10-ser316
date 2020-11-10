package main.java;

import main.java.Stuffing.stuffing;

public class Main {
    
    /**
     * Basic main runner class.
     *
     */
    
    public static void main(String[] args) {
        // Fill me in!

        Bear bear1 = new Bear(stuffing.FOAM);
        Bear bear2 = new Bear(stuffing.BASE);
        Bear bear3 = new Bear(stuffing.DOWN);

        bear1.casing = new Casing(2, "expensive casing");

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

        bear1.clothing.add(new Clothing(5, "Cool sunglasses"));
        System.out.println("Bear with two clothing object");
        bear1.clothing.add(new Clothing(5, "Hat2"));
        bear1.clothing.add(new Clothing(5, "Hat"));
        bear1.clothing.add(new Clothing(5, "Sunglasses"));
        bear1.clothing.add(new Clothing(4, "Shoes"));
        bear1.clothing.add(new Clothing(3, "Jacket"));
        bear1.clothing.add(new Clothing(2, "Glove"));
        bear1.clothing.add(new Clothing(2, "OtherGlove"));
        System.out.println(workshop.calculateSavings());
    }
}
