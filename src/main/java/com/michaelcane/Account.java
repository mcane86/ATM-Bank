package com.michaelcane;


import java.util.ArrayList;

public class Account {

    private AccountTypes accountType;
    private String accountNumber;
    private double balance = 00.00;
    private AccountStatus accountStatus = AccountStatus.OPEN;
    private OverdraftPrevention overdraftPrevention = OverdraftPrevention.ENABLED;
    private ArrayList<String> transactionHistory;

    public void accountGenerator() {
        int number = (int)Math.random();
        int newNumber = number * 1000;
        this.accountNumber = "579-866-"+ newNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String printBalance() {
        if (accountStatus.equals(AccountStatus.OFAC)) {
            return "We're sorry, we cannot process your transaction at this time.";
        } else {
            return "Your current balance is $" + balance + ".";
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public OverdraftPrevention getOverdraftPrevention() {
        return overdraftPrevention;
    }

    public void setOverdraftPrevention(OverdraftPrevention overdraftPrevention) {
        this.overdraftPrevention = overdraftPrevention;
    }

    public Account() {
        transactionHistory = new ArrayList<String>();
        accountGenerator();
    }

    public void credit(double amount) {
        if(accountStatus.equals(AccountStatus.OFAC) || accountStatus.equals(AccountStatus.FROZEN) || accountStatus.equals(AccountStatus.CLOSED)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            balance += amount;
        }
    }

    public void debit(double amount) {
        if(accountStatus.equals(AccountStatus.OFAC) || accountStatus.equals(AccountStatus.FROZEN) || accountStatus.equals(AccountStatus.CLOSED)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            balance -= amount;
        }
    }

    public void debitOverdraftPreventionTest(double amount) {
        if(overdraftPrevention.equals(OverdraftPrevention.ENABLED) && amount > balance) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            debit(amount);
        }
    }


}
