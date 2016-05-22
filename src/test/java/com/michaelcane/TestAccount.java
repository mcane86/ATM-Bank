package com.michaelcane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAccount {

    Account account;
    @Before public void setupInitializer() {
        account = new Account();
    }

    @Test
    public void testBalance() {
        account.setBalance(1000.00);
        account.credit(500.55);
        String expectedValue = "Your current balance is $1500.55.";
        String actualValue = account.printBalance();
        assertEquals("Output should be $1500", expectedValue, actualValue);
    }

    @Test
    public void testBalanceFailure() {
        account.setAccountStatus(AccountStatus.OFAC);
        String expectedValue = "We're sorry, we cannot process your transaction at this time.";
        String actualValue = account.printBalance();
        assertEquals("Out should be failed", expectedValue, actualValue);
    }

    @Test
    public void testBalanceOFAC() {
        account.setAccountStatus(AccountStatus.OFAC);
        AccountStatus expectedValue = AccountStatus.OFAC;
        AccountStatus actualValue= account.getAccountStatus();
        assertEquals("Output should be OFAC", expectedValue, actualValue);
    }

    @Test
    public void testCredit() {
        account.credit(20.00);
        String expectedValue = "Your current balance is $20.0.";
        String actualValue = account.printBalance();
        assertEquals("Out should be $45.0", expectedValue, actualValue);
    }

    @Test
    public void testSetOverdraftProtectionStatus() {
        OverdraftPrevention beforeChange = account.getOverdraftPrevention();
        account.setOverdraftPrevention(OverdraftPrevention.DISABLED);
        OverdraftPrevention afterChange = account.getOverdraftPrevention();
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void testDebitOverdraftPreventionTest() {
        account.credit(100.00);
        String expectedValue= "Your current balance is $76.4.";
        account.debitOverdraftPreventionTest(23.60);
        String actualValue = account.printBalance();
        assertEquals("Output should be 101.40", expectedValue, actualValue);
    }
}
