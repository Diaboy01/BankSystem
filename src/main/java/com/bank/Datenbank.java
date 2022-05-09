package com.bank;

import java.util.HashMap;

public class Datenbank {

    private static final Datenbank INSTANCE = new Datenbank();
    public static Datenbank getInstance() {
        return INSTANCE;
    }

    // Wir wollen kein neues Objekt erzeugen, deswegen private.
    // Wir arbeiten erstmal nicht mit SQL, sondern speichern alles im Arbeitsspeicher.
    private final HashMap<String, Account> accounts; // die primitive Datenbank
    private Datenbank() {
        accounts = new HashMap<>();
    }

    public boolean existsAccount(IBAN iban) {
        return accounts.containsKey(iban.toString());
    }

    public Account getAccount(IBAN iban) {
        return accounts.get(iban.toString());
    }

    public void delete(IBAN iban) {
        accounts.remove(iban.toString());
    }

    public void create(IBAN iban, Account account) {
        accounts.put(iban.toString(), account);
    }
}