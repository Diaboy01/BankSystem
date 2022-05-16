package com.bank;

import org.junit.Assert;
import org.junit.Test;

//TODO Test geht noch nicht


public class IBANTest {

    @Test
    public void testValid() {
        String country = "DE";
        int checkSum = 58; // 48 original
        String bank = "52010519";
        String account = "6843116031";

        IBAN iban = new IBAN(country + checkSum + bank + account);

        if(!iban.getCountry().equals(country)) {
            Assert.assertEquals(country, iban.getCountry());
        }

        if(!iban.getCheckSum().equals(String.valueOf(checkSum))) {
            Assert.assertEquals(String.valueOf(checkSum), iban.getCheckSum());
        }

        if(!iban.getBankNumber().equals(bank)) {
            Assert.assertEquals(bank, iban.getBankNumber());
        }

        if(!iban.getAccountNumber().equals(account)) {
            Assert.assertEquals(account, iban.getAccountNumber());
        }
        Assert.assertTrue(true);
    }

}
