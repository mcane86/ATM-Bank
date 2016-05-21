package com.michaelcane;

public class Savings extends Account {

    private double interestRate = .0105;

    public Savings() {
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
