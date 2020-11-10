package main.java;

public class Embroidery {
    final static double pricePerLetter = 1.00;  //SER316 TASK 2 SPOTBUGS FIX
    double price;
    String embroidText;

    public Embroidery(String embroidery) {
        this.embroidText = embroidery;
        this.price = embroidery.length() * pricePerLetter;
    }
}
