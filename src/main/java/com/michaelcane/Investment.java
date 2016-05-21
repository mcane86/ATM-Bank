package com.michaelcane;

public class Investment extends Account {

    private double interestRate = .0155;

    public Investment() {
        super();
    }

    public boolean checkForClosable() {
        double currentBalance = getBalance();
        if (currentBalance != 0.00) {
            System.out.println("I'm sorry, you still have money in this account. Please take your money out and try again.");
            return false;
        } else {
            return true;
        }
    }
}
