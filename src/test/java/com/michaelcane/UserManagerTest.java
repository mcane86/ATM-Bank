package com.michaelcane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserManagerTest {

    UserManager userManager;
    @Before public void setupInitializer() {
        userManager = new UserManager();
    }

    @Test
    public void testPasswordChecker() throws Exception {
        String expected = "Congrats, your password is good.";
        String actual= userManager.passwordChecker("sunday");
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordCheckerToFailure() throws Exception {
        String expected = "Sorry, that password is taken.";
        userManager.passwordChecker("sunday");
        String actual = userManager.passwordChecker("sunday");
        assertEquals(expected, actual);
    }

    @Test
    public void testUserLookUp() {
        User user = new User("Tommy Tutone");
        String password = "sunday";
        userManager.userPasswordList.put(password, user);
        String expected = "Tommy Tutone";
        String actual = userManager.userLookUp(password).getUserName();
        assertEquals(expected, actual);
    }

    @Test
    public void testUserLookupToFailure() {
        User user = new User("Tommy Tutone");
        String password = "sundaY";
        String expected = null;
        User actual = userManager.userLookUp(password);
        assertEquals("This should return the failure message", expected, actual);
    }
}