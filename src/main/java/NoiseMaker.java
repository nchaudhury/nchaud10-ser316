package main.java;

/**
 * Basic Noisemaker class.
 *
 */

public class NoiseMaker {
    public double price;

    Location spot;

    public NoiseMaker() {
        this(Location.CENTERBODY); //SER316 TASK 2 SPOTBUGS FIX
    }
    
    /**
     * Basic Noisemaker constructor using label, recoring, and location.
     * @param location 
     *
     */
    
    public NoiseMaker(Location location) { //SER316 TASK 2 SPOTBUGS FIX
        this.spot = location; //SER316 TASK 2 SPOTBUGS FIX
        switch (location) {
            case CENTERBODY:
                this.price = 10;
                break;
            default:
                this.price = 5;
                break;
        }
    }


    public enum Location {
        RIGHT_HAND, LEFT_HAND, RIGHT_FOOT, LEFT_FOOT, CENTERBODY
    }
}


