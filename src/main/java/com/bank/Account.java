package com.bank;

import java.util.LinkedList;

public class Account {

    // Geheimnisprinzip deswegen private
    private final IBAN iban; // IBAN für die Suche in einer Datenbank (sollte nicht überschrieben werden)
    private String firstName, lastName; // Informationen über den Accounthalter (wird aktuell ignoriert)
    private double balance; // Kontostand
    private LinkedList<Credit> credits; // Kredite

    public Account(String country, String bankCode, String firstName, String lastName) {
        this(IBAN.generateIBAN(country, bankCode), firstName, lastName, 0); // greifen auf den anderen Konstruktor zu
    }

    // Zum Übertragen der UUID aus der Datenbank.
    public Account(IBAN iban, String firstName, String lastName, double balance) {
        this(iban);
        setFirstName(firstName);
        setLastName(lastName);
        setBalance(balance);
    }

    // zum Erstellen eines neuen Accounts.
    public Account(IBAN iban) {
        if(iban == null) {
            throw new NullPointerException("'iban' kann nicht null sein.");
        }

        this.iban = iban;
        this.credits = new LinkedList<>();
    }

    public void setLastName(String lastName) {
        if(lastName == null) {
            throw new NullPointerException("'lastName' kann nicht null sein.");
        }

        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null) {
            throw new NullPointerException("'firstName' kann nicht null sein.");
        }

        this.firstName = firstName;
    }

    public void setBalance(double balance) {
        if(balance < Bank.MIN_BALANCE) {
            throw new NullPointerException("'balance' kann nicht kleiner als " + Bank.MIN_BALANCE + " sein. (Wert: " + balance + ")");
        }

        this.balance = balance;
    }

    public void addBalance(double value) {
        double newValue = getBalance() + value;
        if(newValue < Bank.MIN_BALANCE) newValue = Bank.MIN_BALANCE;
        setBalance(newValue);
    }

    public void removeBalance(double value) {
        double newValue = getBalance() - value;
        if(newValue < Bank.MIN_BALANCE) newValue = Bank.MIN_BALANCE;
        setBalance(newValue);
    }

    public void clearBalance() {
        setBalance(0);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public IBAN getIBAN() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public String getBank() {
        return iban.getBankNumber();
    }

    public String getCountry() {
        return iban.getCountry();
    }

    public String getAccountNumber() {
        return iban.getAccountNumber();
    }

    public LinkedList<Credit> getCredits() {
        return credits;
    }

    public void setCredits(LinkedList<Credit> credits) {
        this.credits = credits;
    }

    public void addCredit(double credit) {
        if(credit < Bank.MIN_BALANCE) return;
        credits.add(new Credit(credit));
    }

    public Credit removeLastCredit() {
        return credits.removeLast();
    }
}