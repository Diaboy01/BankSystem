package com.bank;

public class Bank {

    public static final double MIN_BALANCE = 0, MIN_CREDIT = 0;
    public static final int INTEREST = 4; // 4% Zinsen

    private final String bankCode;
    public Bank(String bankCode) {
        this.bankCode = bankCode;
    }

    public Account createAccount(String country) {
        IBAN iban = IBAN.generateIBAN(country, bankCode);

        Account account = new Account(iban);
        Datenbank.getInstance().create(iban, account);

        return account;
    }

    public void deleteAccount(IBAN iban) {
        if(!iban.getBankNumber().equals(bankCode)) {
            return;
        }

        Datenbank.getInstance().delete(iban);
    }

    public Account getAccount(IBAN iban) {
        if(!iban.getBankNumber().equals(bankCode)) {
            return null;
        }

        return Datenbank.getInstance().getAccount(iban);
    }

    public boolean hasAccount(IBAN iban) {
        if(!iban.getBankNumber().equals(bankCode)) {
            return false;
        }

        return Datenbank.getInstance().existsAccount(iban);
    }

    public String getBankCode() {
        return bankCode;
    }
}
