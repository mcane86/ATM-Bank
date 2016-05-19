package com.michaelcane;


public class Account {

    private AccountTypes accountType;
    private Long accountNumber;
    private double balance;
    private String accountHoldersName;
    private double interestRate;
    private AccountStatus accountStatus = AccountStatus.OPEN;
    private OverdraftPrevention overdraftPrevention = OverdraftPrevention.ENABLED;

    public void getBalance() {
        if (accountStatus.equals(AccountStatus.OFAC)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            System.out.println("Your current balance is " + balance + ".");
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

    public void setAccountHoldersName(String accountHoldersName) {
        this.accountHoldersName = accountHoldersName;
    }

    public void setOverdraftPrevention(OverdraftPrevention overdraftPrevention) {
        this.overdraftPrevention = overdraftPrevention;
    }

    public Account(String name) {
        this.accountHoldersName = name;
    }

    public void credit(int amount) {
        if(accountStatus.equals(AccountStatus.OFAC) || accountStatus.equals(AccountStatus.FROZEN) || accountStatus.equals(AccountStatus.CLOSED)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            balance += amount;
        }
    }

    public void debit(int amount) {
        if(accountStatus.equals(AccountStatus.OFAC) || accountStatus.equals(AccountStatus.FROZEN) || accountStatus.equals(AccountStatus.CLOSED)) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            balance -= amount;
        }
    }

    public void debitOverdraftPreventinTest(int amount) {
        if(overdraftPrevention.equals(OverdraftPrevention.ENABLED) && amount > balance) {
            System.out.println("We're sorry, we cannot process your transaction at this time.");
        } else {
            debit(amount);
        }
    }


}
