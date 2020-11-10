package main.java;

/**
 * Basic Stuffing class.
 *
 */

public class Stuffing {
    public enum StuffingEnum {  //SER316 TASK 2 SPOTBUGS FIX
        BASE, DOWN, FOAM
    }

    //SER316 TASK 2 SPOTBUGS FIX
    int price;
    
    /**
     * Basic Stuffing Constructor.
     *
     */
    
    public Stuffing(StuffingEnum interiorStuffing) {  //SER316 TASK 2 SPOTBUGS FIX

        switch (interiorStuffing) {
            case BASE:
                this.price = 30;
                break;
            case DOWN:
                this.price = 40;
                break;
            case FOAM:
                this.price = 50;
                break;
            default:
                this.price = 30;  //SER316 TASK 2 SPOTBUGS FIX
                break;
        }
    }
}
