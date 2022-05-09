package com.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


//TODO Test geht noch nicht

public class IBANTest {

    @Test
    @DisplayName("IBAN Werte werden korrekt ausgegeben.")
    public boolean testValid() {
        String country = "DE";
        int checkSum = 58; // 48 original
        String bank = "52010519";
        String account = "6843116031";

        IBAN iban = new IBAN(country + checkSum + bank + account);

        if(!iban.getCountry().equals(country)) {
            Assertions.assertEquals(country, iban.getCountry());
        }

        if(!iban.getCheckSum().equals(String.valueOf(checkSum))) {
            Assertions.assertEquals(String.valueOf(checkSum), iban.getCheckSum());
        }

        if(!iban.getBankNumber().equals(bank)) {
            Assertions.assertEquals(bank, iban.getBankNumber());
        }

        if(!iban.getAccountNumber().equals(account)) {
            Assertions.assertEquals(account, iban.getAccountNumber());
        }

        return true;
    }

}
