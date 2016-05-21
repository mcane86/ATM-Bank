package com.michaelcane;

public class User {

    private Checking checking;
    private Investment investment;
    private Savings savings;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public User(String userName) {
        this.userName = userName;
        checking = new Checking();
        investment = new Investment();
        savings = new Savings();
    }

}
