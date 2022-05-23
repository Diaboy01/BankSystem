package com.bank;

import com.bank.command.CommandRegistry;

import java.text.DecimalFormat;

public class Main {

    public static Bank bank;
    public static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###.##€");

    public static void main(String[] args) {
        String bankcode = "52010519"; // welche Bank sind wir?
        assert bankcode.length() == 8; // Überprüfe ob der Bankcode valide ist.
        bank = new Bank(bankcode);
        System.out.println("Herzlich Willkommen!");
        CommandRegistry.getInstance();
    }
}