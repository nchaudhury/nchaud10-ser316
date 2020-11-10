package main.java;

public class Clothing implements Comparable<Clothing> {
    public double price;
     //SER316 TASK 2 SPOTBUGS FIX

    public Clothing() {
        this(4.00);          //SER316 TASK 2 SPOTBUGS FIX


    }

    public Clothing(double price) {
        this.price = price;
         //SER316 TASK 2 SPOTBUGS FIX
    }

    @Override
    public int compareTo(Clothing clothes) {
        if (clothes.equals(this)){//SER316 TASK 2 SPOTBUGS FIX
            return 0;
        }
        return new Double(this.price).compareTo(clothes.price);
        
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
        Clothing other = (Clothing) obj;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
            return false; 
        }
        return true;
    }
}
