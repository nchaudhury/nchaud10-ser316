package main.java;

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
