package main.java;

import java.util.UUID;

/**
 * This a class for customer in the bear workshop.
 */

public class Customer {
    int age;

    // customer has a name and a customer id
    Customer parent;

    // Customer lives in a state  //SER316 TASK 2 SPOTBUGS FIX
    public String state;

    /**
     * Default ctor with state
     */
    public Customer(String state) {
        this.state = state;
        this.age = 18;
    }

    /**
     * Parameterized ctor for Customers
     * 
     * @param age      int age of customer
     * @param custumer reference to guardian or null
     */
    public Customer(int age, String state, Customer custumer) {  //SER316 TASK 2 SPOTBUGS FIX
        this.parent = custumer;
        this.age = age;


        this.state = state;
    }

}