package com.michaelcane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingTest {

    Checking checking;
    double delta = 1e-15;
    @Before
    public void setUp() throws Exception {
        checking = new Checking();
    }

    @Test
    public void testPrintBalance() {
        checking.setBalance(100.00);
        String expected = "Your current balance is $100.0.";
        String actual = checking.printBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testCredit() {
        double beforeCredit = checking.getBalance();
        checking.credit(100.00);
        double afterCredit = checking.getBalance();
        assertNotEquals(beforeCredit, afterCredit, delta);
    }

    @Test
    public void testDebit() {
        checking.setBalance(100.00);
        double beforeDebit = checking.getBalance();
        checking.debit(50.00);
        double afterDebit = checking.getBalance();
        assertNotEquals(beforeDebit, afterDebit);
    }

}