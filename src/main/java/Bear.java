package main.java;

import java.util.LinkedList;

import main.java.Stuffing.StuffingEnum;

/**
 * Basic Bear Class.
 *
 */

public class Bear implements Comparable<Bear> {
    public Casing casing;
    public Stuffing stuff;
    public Embroidery ink;
    public LinkedList<NoiseMaker> noisemakers; // accessory
    public LinkedList<Clothing> clothing; // accessory
    double price = 0.0;
    // bear has a shell (requ)
    // bear has stuffing (req)
    // bear has a tattoo/emroider or not (opt)
    // bear has a noisemaker (opt)
    
    /**
     * Basic Bear Constructor.
     *
     */
    
    public Bear() {
        this.casing = new Casing(); //SER316 TASK 2 SPOTBUGS FIX
        this.stuff = new Stuffing(StuffingEnum.BASE); //SER316 TASK 2 SPOTBUGS FIX
        noisemakers = new LinkedList<>(); 
        clothing = new LinkedList<>(); //SER316 TASK 2 SPOTBUGS FIX
        ink = new Embroidery(""); //SER316 TASK 2 SPOTBUGS FIX
        price = 0.0;
    }
    
    /**
     * Basic Bear Constructor with Stuffing.
     *
     */
    
    public Bear(StuffingEnum stuff) {
        this.casing = new Casing();
        this.stuff = new Stuffing(stuff);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0.0;
    }
    
    public void setPrice(double incomingPrice) {
        this.price = incomingPrice;
    }

    /**
     * Add noise to bear. 
     *
     */
    
    public boolean addNoise(NoiseMaker noise) {
        if (this.noisemakers.size() >= 5) {
            return false;
        } else {
            for (NoiseMaker noisemaker : noisemakers) {
                if (noise.spot == noisemaker.spot) {
                    return false;
                }
            }
            noisemakers.add(noise);
            return true;
        }
    }

    @Override
    public int compareTo(Bear bear) {
        return new Double(this.price).compareTo(bear.price);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; 
        }
        if (obj == null) {
            return false; 
        }
        if (getClass() != obj.getClass()) {
            return false; 
        }
        Bear other = (Bear) obj;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
            return false; 
        }
        return true;
    }
}