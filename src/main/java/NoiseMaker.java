package main.java;

/**
 * Basic Noisemaker class.
 *
 */

public class NoiseMaker {
    public double price;
    String label;
    String recording;

    Location spot;

    public NoiseMaker() {
        this("Default Noise", "I wuv you", Location.CENTERBODY);
    }
    
    /**
     * Basic Noisemaker constructor using label, recoring, and location.
     *
     */
    
    public NoiseMaker(String label, String recording,
                      Location location) {
        this.label = label;
        this.recording = recording;
        this.spot = location;
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


