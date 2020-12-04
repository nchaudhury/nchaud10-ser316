package main.java;

import java.util.*;

// This class provides functionality for a BearWorkshop class.
public class BearWorkshop implements BearWorkshopInterface {
    // Workshop has a collection of bears
    // Workshop has a customer
    Customer customer;
    List<Bear> BearCart;

    /**
     * Default constructor for the Bear Workshop.
     */
    
    public BearWorkshop() {
        this("AZ");
    }

    /**
     * This is a parameterized ctor for a BearWorkshop.
     * 
     * @param state customer is in.
     */
    
    public BearWorkshop(String state) {
        BearCart = new LinkedList<>();
        customer = new Customer(state);
    }

    /**
     * This is a convenience method to calculate the cost of bears in the shopping
     * cart for a customer in the BearFactory.
     * 
     * @param bear to get cost of.
     * @return double representation of bear cost. 
     * TODO: test me and fix me in assignment 3.
     */
    
    @Override
    public double getCost(Bear bear) {
        Collections.sort(bear.clothing);
        double numFreeTemp = bear.clothing.size() / (double)(3);
        int numFree = (int) Math.floor(numFreeTemp); // floored out //SER316 TASK 2 SPOTBUGS FIX
        ArrayList<Clothing> freeClothes = new ArrayList<>();

        List<Clothing> tempClothes = bear.clothing;
        Collections.sort(tempClothes);
        for (int i = tempClothes.size() - 1; i >= 0; i--) {
            if ((tempClothes.size() - i - 1) < numFree) {
                freeClothes.add(tempClothes.get(i));
            } else {
                bear.price += tempClothes.get(i).price;
            }

        }

        for (NoiseMaker noise : bear.noisemakers) {
            bear.price += noise.price;
        }

        if (!(bear.ink.embroidText.equals(""))) { // better condition
            bear.price += bear.ink.price;
        }

        bear.price += bear.stuff.price;

        bear.price += bear.casing.priceModifier; // changed to be + from multiplication. It does not make sense for the
                                                 // price of the clothing to go up at the end due to casing modifier.
        if ((bear.clothing.size() + bear.noisemakers.size() - freeClothes.size()) >= 10) {
            bear.price = bear.price - ((.1) * bear.price);
        }
        return bear.price;
    }

    // Function to get the raw cost of a bear without any discounts
    // TODO: test me and fix me in assignment 3
    
    public double getRawCost(Bear bear) {
        double bearPrice = 0.0;
        for (int i = 0; i < bear.clothing.size(); i++) {
            bearPrice += bear.clothing.get(i).price;
        }

        for (NoiseMaker noise : bear.noisemakers) {
            bearPrice += noise.price;

        }

        if (!(bear.ink.embroidText.equals(""))) {
            bearPrice += bear.ink.price;

        }

        bearPrice += bear.stuff.price;
        bearPrice += bear.casing.priceModifier;

        return bearPrice;
    }

    /**
     * Utility method to calculate tax for purchases by customers in different
     * states. You can assume these taxes are what we want, so they are not wrong.
     * 
     * @return
     */
    
    public double calculateTax() {
        double tax=1.05;
        
            if( customer.state.equals("AZ"))
            {
                tax = 1.07;
            }
            else if( customer.state.equals("NY"))
            {
                tax = 1.09;
            }
            else if( customer.state.equals("DC"))
            {
                tax = 1.105;
            }
            else if( customer.state.equals("CA"))
            {
                tax = 1.11;
            }
        return tax;
    }

    /**
     * Utility method to add a bear to the BearFactory.
     * 
     * @param bear to add.
     * @return true if successful, false otherwise. 
     * TODO: test me and fix me in assignment 3.
     */
    
    @Override
    public boolean addBear(Bear bear) {
        return (this.BearCart.add(bear));
    }

    // Simple means to remove a bear
    @Override
    public boolean removeBear(Bear bear) {
        return (this.BearCart.remove(bear));
    }

    /**
     * This is a function to allow customers to checkout with their TODO: Test me
     * and fix me in assignment 3
     * 
     * @return
     */
    @Override
    public double checkout() {
        if (this.customer.age <= 13) {
            if (this.customer.parent.age < 18)
                System.out.println("Guardian is too young");
            return -1;
        }
        double raw = 0.0;
        for (int x = 0; x < this.BearCart.size(); x++) {
            Bear bear = this.BearCart.get(x);
            raw += getRawCost(bear);

        }
        double savings = this.calculateSavings();
        double cost = raw - savings;

        return cost * calculateTax();
    }

    /**
     * This method returns the savings for advertised bundle savings. Specifically,
     * - Bears are Buy 2 bears, get a third one free. It is always the cheapest bear
     * that is free - It is 10% off the cost of a bear when a single bear has 10 or
     * more accessories (clothes and otherwise) that the customer pays for (so if
     * clothes are free these do not count). - Clothes are buy 2, get one free on
     * each bear. Always the cheapest clothes are free TIP: the implemented savings
     * method in the BearWorkshop1-5 do not use the getCost method implemented in
     * this base class. They implement their own savings calculation All of them do
     * however use the getRawCost method implemented in this base class.
     * 
     * @return the savings if the customer would check out as double
     */
    public double calculateSavings() {
        double runningTotalRaw = 0.0;
        double runningTotalSaved = 0.0;

        for (int x = 0; x < this.BearCart.size(); x++) {
            Bear bear = this.BearCart.get(x);
            bear.price = 0.0; // reset number to get accurate savings
            runningTotalRaw += getRawCost(bear);
            ArrayList<Clothing> freeClothes = new ArrayList<>();
            if (bear.clothing.size() > 0) {
                LinkedList<Clothing> tempClothes = bear.clothing;
                double numFreeTemp = tempClothes.size() / (double)3;
                int numFree = (int) Math.floor(numFreeTemp); //SER316 TASK 2 SPOTBUGS FIX
                // Buy 2 get one free for now

                for (int i = tempClothes.size() - 1; i >= 0; i--) { // Sort did not work on clothing.
                    if ((tempClothes.size() - i - 1) < numFree) {
                        freeClothes.add(tempClothes.get(i));
                    } else {
                        bear.price += tempClothes.get(i).price;
                    }

                }

            }

            if (bear.noisemakers.size() > 0) {
                for (NoiseMaker noise : bear.noisemakers) {
                    bear.price += noise.price;
                }
            }

            if (!(bear.ink.embroidText.equals(""))) {
                bear.price += bear.ink.price;
            }

            bear.price += bear.stuff.price;
            bear.price += bear.casing.priceModifier;

            // 10 accessories or more, 10 percent off
            if ((bear.clothing.size() + bear.noisemakers.size() - freeClothes.size()) >= 10) {
                bear.price = bear.price - ((.1) * bear.price);
            }

            runningTotalSaved += bear.price;
            ;
        }

        if (this.BearCart.size() > 2) {
            // add buy 2 get 1 free functionality
            double temp2 = this.BearCart.size() / (double) 3 ;
            int temp = (int) Math.floor(temp2);
            for (int i = 0; i < this.BearCart.size(); i++) {
                if (i < temp) {
                    runningTotalSaved -= this.BearCart.get(i).price;
                }
            }
        }

        return (runningTotalRaw - runningTotalSaved);
    }
}