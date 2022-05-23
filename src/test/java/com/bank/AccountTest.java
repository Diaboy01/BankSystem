package com.bank;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
    @Test
    public void testAccountBalance() {
        Bank bank = new Bank("12345678");
        IBAN iban = bank.createAccount("DE").getIBAN();
        Account account = new Account(iban);
        double currentBalance = account.getBalance();
        account.addBalance(10.0);
        Assert.assertTrue(account.getBalance() == currentBalance + 10.0);
    }
}
