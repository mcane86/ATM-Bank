package com.michaelcane;

import java.util.HashMap;
import java.util.HashSet;

public class UserManager {

    HashMap<String, User> userPasswordList = new HashMap();

    HashSet<String> passwords = new HashSet<String>();

    public String passwordChecker(String possiblePassword) {
        if(passwords.add(possiblePassword)) {
            return "Congrats, your password is good.";
        } else {
            return "Sorry, that password is taken.";
        }
    }

    public User userLookUp(String password) {
        if(userPasswordList.get(password) == null) {
            System.out.println("I'm sorry, that password was incorrect.");
            return userPasswordList.get(password);
        } else {
            return userPasswordList.get(password);
        }
    }

}
