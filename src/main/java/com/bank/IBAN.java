package com.bank;

import java.util.Random;

public class IBAN {

    private final String iban;
    public IBAN(String iban) {
        this.iban = iban;
    }

    public String getCountry() {
        if(iban.length() != 22) return "null";
        return iban.substring(0, 2);
    }

    public String getCheckSum() {
        if(iban.length() != 22) return "null";
        return iban.substring(2, 4);
    }

    public String getAccountNumber() {
        if(iban.length() != 22) return "null";
        return iban.substring(12);
    }

    public String getBankNumber() {
        if(iban.length() != 22) return "null";
        return iban.substring(4, 12);
    }

    @Override
    public String toString() {
        return iban;
    }

    // Es soll nicht jedes mal ein neuer Random erstellt werden. Nur die Klasse greift darauf zu, also private.
    private static final Random RANDOM = new Random();
    private static final int GLOBAL_CHECK_SUM = 98;

    /**
     * Erstellt eine IBAN anhand der gegebenen Parameter.
     *
     * @param country Ländercode
     * @param bankNumber Bankcode
     * @param accountNumber Accountnummer
     * @return Gibt die zum Account und Bank dazugehörige IBAN zurück.
     */
    public static IBAN getIBAN(String country, String bankNumber, String accountNumber) {
        int value = 0;

        for(char c : bankNumber.toCharArray()) {
            value += c;
        }

        for(char c : accountNumber.toCharArray()) {
            value += c;
        }

        int checkSum = value % GLOBAL_CHECK_SUM;
        return new IBAN(country + checkSum + bankNumber + accountNumber);
    }

    /**
     * Erstelle eine neue IBAN anhand eines Länder- und Bankcodes.
     *
     * @param country Ländercode
     * @param bankNumber Bankcode
     * @return Gibt eine individuelle IBAN zurück.
     */
    public static IBAN generateIBAN(String country, String bankNumber) {
        int value = 0;
        for(char c : bankNumber.toCharArray()) {
            value += c;
        }

        // erstellen 10 zufällige Zahlen
        StringBuilder accountNumber = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            int randomNumber = RANDOM.nextInt(10);

            accountNumber.append(randomNumber);
            value += randomNumber;
        }

        int checkSum = value % GLOBAL_CHECK_SUM;
        IBAN iban = new IBAN(country + checkSum + bankNumber + accountNumber);

        if(Datenbank.getInstance().existsAccount(iban)) {
            return generateIBAN(country, bankNumber); // IBAN existiert, fange von vorne an. Statistisch gesehen macht das keinen Aufwand.
        }

        return iban;
    }
}