package com.bank;

import org.junit.Assert;
import org.junit.Test;

public class BankTest {

    @Test
    public void testBankAccountCreation() {
        Bank bank = new Bank("12345678");
        IBAN iban = bank.createAccount("DE").getIBAN();
        Assert.assertTrue(bank.hasAccount(iban));
    }
}
