package com.michaelcane;

public class Account {

    private String accountNumber;
    private double balance = 00.00;
    private AccountStatus accountStatus = AccountStatus.OPEN;
    private OverdraftPrevention overdraftPrevention = OverdraftPrevention.ENABLED;


    protected void accountNumberGenerator() {
        int number = (int)Math.random();
        int newNumber = number * 1000;
        this.accountNumber = "579-866-"+ newNumber;
    }

    protected String getAccountNumber(String accountType) {
        return "Your " + accountType + " account number is " + accountNumber;
    }

    protected double getBalance() {
        return balance;
    }

    protected String printBalance() {
        if (accountStatus.equals(AccountStatus.OFAC)) {
            return "We're sorry, we cannot process your transaction at this time.";
        } else {
            return "Your current balance is $" + balance + ".";
        }
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    protected AccountStatus getAccountStatus() {
        return accountStatus;
    }

    protected void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    protected OverdraftPrevention getOverdraftPrevention() {
        return overdraftPrevention;
    }

    protected void setOverdraftPrevention(OverdraftPrevention overdraftPrevention) {
        this.overdraftPrevention = overdraftPrevention;
    }

    protected void credit(double amount) {
        if(accountStatus.equals(AccountStatus.OFAC) || accountStatus.equals(AccountStatus.FROZEN) || accountStatus.equals(AccountStatus.CLOSED)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            balance += amount;
        }
    }

    protected void debit(double amount) {
        if(accountStatus.equals(AccountStatus.OFAC) || accountStatus.equals(AccountStatus.FROZEN) || accountStatus.equals(AccountStatus.CLOSED)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            balance -= amount;
        }
    }

    protected void debitOverdraftPreventionTest(double amount) {
        if(overdraftPrevention.equals(OverdraftPrevention.ENABLED) && amount > balance) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            debit(amount);
        }
    }


}
