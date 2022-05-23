package com.bank;

import org.junit.Assert;
import org.junit.Test;

public class IBANTest {

    @Test
    public void testValidIBANOutput() {
        String country = "DE";
        String checkSum = "48";
        String bank = "52010519";
        String account = "6843116031";

        IBAN iban = new IBAN(country + checkSum + bank + account);

        Assert.assertEquals(country, iban.getCountry());
        Assert.assertEquals(checkSum, iban.getCheckSum());
        Assert.assertEquals(bank, iban.getBankNumber());
        Assert.assertEquals(account, iban.getAccountNumber());
    }
}
