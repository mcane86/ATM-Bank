package com.michaelcane;

public class User {

    UserManager userManager = new UserManager();
    private Checking checking;
    private Investment investment;
    private Savings savings;
    private String userName;



    public String getUserName() {
        return userName;
    }

    public Checking getChecking() {
        return checking;
    }

    public Investment getInvestment() {
        return investment;
    }

    public Savings getSavings() {
        return savings;
    }

    public User(String userName) {
        this.userName = userName;
        checking = new Checking();
        investment = new Investment();
        savings = new Savings();
    }

}
