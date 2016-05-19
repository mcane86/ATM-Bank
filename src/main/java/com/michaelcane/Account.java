package com.michaelcane;


public class Account {

    private AccountTypes accountType;
    private Long accountNumber;
    private double balance;
    private String accountHoldersName;
    private double interestRate;
    private AccountStatus accountStatus;
    private OverdraftPrevention overdraftPrevention;

    public double getBalance() {
        if (accountStatus.equals(AccountStatus.OFAC)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        }
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setAccountHoldersName(String accountHoldersName) {
        this.accountHoldersName = accountHoldersName;
    }

    public void setOverdraftPrevention(OverdraftPrevention overdraftPrevention) {
        this.overdraftPrevention = overdraftPrevention;
    }

    public Account(String name) {
        this.accountHoldersName = name;
    }

}
