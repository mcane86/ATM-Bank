package com.michaelcane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAccount {

    double delta = 1e-15;

    Account account;
    @Before public void setupInitializer() {
        account = new Account("Mike Cane");
    }

    @Test
    public void testBalance() {
        account.setBalance(1500.00);
        double expectedValue = 1500.00;
        double actualValue = account.getBalance();
        assertEquals("Output should be $1500", expectedValue, actualValue, delta);
    }

    @Test
    public void testBalanceOFAC() {
        account.setAccountStatus(AccountStatus.OFAC);
        AccountStatus expectedValue = AccountStatus.OFAC;
        AccountStatus actualValue= account.getAccountStatus();
        assertEquals("Output should be OFAC", expectedValue, actualValue);
    }
}
