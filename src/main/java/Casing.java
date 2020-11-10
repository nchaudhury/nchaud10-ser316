package main.java;

/**  Class: Casing.java   
 * 	 Description: Handles Bear Casing logic such as description and price.
 * 
 * */
public class Casing {
    double priceModifier;
    public Casing() {
        this(0.00);         //SER316 TASK 2 SPOTBUGS FIX

    }

    public Casing(double price) {         //SER316 TASK 2 SPOTBUGS FIX

        setPrice(price);         //SER316 TASK 2 SPOTBUGS FIX
    }

 

    public void setPrice(double price) {
        this.priceModifier = price;
    }

}
