package com.michaelcane;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestClosable {

    Checking checking;
    Investment investment;
    Savings savings;

    @Before public void setupInit() {
        checking = new Checking();
        investment = new Investment();
        savings = new Savings();
    }

    @Test
    public void testCheckingClosable() {
        checking.setBalance(100.00);
        boolean expected = false;
        boolean actual = checking.checkForClosable();
        assertEquals(expected, actual);
    }

    @Test
    public void testInvestmentClosable() {
        investment.setBalance(100.00);
        boolean expected = false;
        boolean actual = investment.checkForClosable();
        assertEquals(expected, actual);
    }

    @Test
    public void testSavingsClosable() {
        savings.setBalance(100.00);
        boolean expected = false;
        boolean actual = savings.checkForClosable();
        assertEquals(expected, actual);
    }
}
