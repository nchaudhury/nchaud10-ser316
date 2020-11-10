package main.java;

/**
 * Basic Stuffing class.
 *
 */

public class Stuffing {
    public enum stuffing {
        BASE, DOWN, FOAM
    }

    stuffing polyStuffing;
    int price;
    
    /**
     * Basic Stuffing Constructor.
     *
     */
    
    public Stuffing(stuffing interiorStuffing) {

        switch (interiorStuffing) {
            case BASE:
                this.polyStuffing = stuffing.BASE;
                this.price = 30;
                break;
            case DOWN:
                this.polyStuffing = stuffing.DOWN;
                this.price = 40;
                break;
            case FOAM:
                this.polyStuffing = stuffing.FOAM;
                this.price = 50;
                break;
            default:
                this.polyStuffing = stuffing.BASE;
                this.price = 30;
                break;
        }
    }
}
