package com.bank.pro;

import com.bank.*;
import com.bank.command.ICommand;

import java.util.Iterator;
import java.util.LinkedList;

public class PayInterestCommand implements ICommand {

    @Override
    public String getLabel() {
        return "payinterest";
    }

    @Override
    public String getHelpMessage() {
        return "payinterest <iban>";
    }

    @Override
    public void execute(String[] arguments) {
        if(arguments.length != 1) {
            System.out.println(getHelpMessage());
            return;
        }

        IBAN iban = new IBAN(arguments[0]);
        Account account = Main.bank.getAccount(iban);
        if(account == null) {
            System.out.println("Account '" + iban + "' wurde nicht gefunden.");
            return;
        }

        if(account.getCredits().isEmpty()) {
            System.out.println(iban + " besitzt keine Kredite.");
            return;
        }

        double valuePayed = 0;
        int leftToPay = account.getCredits().size();

        LinkedList<Credit> removal = new LinkedList<>();
        for(Credit credit : account.getCredits()) {
            double value = credit.getCurrentValue();
            double pay = credit.getOriginalValue() * (Bank.INTEREST / 100.0);

            if(value - pay <= 0) {
                pay = value;
                removal.add(credit);
            }

            double currentValue = account.getBalance();
            if(currentValue - valuePayed < Bank.MIN_BALANCE) {
                break;
            }

            valuePayed += pay;
            credit.setCurrentValue(value - pay);
            leftToPay--;
        }

        account.removeBalance(valuePayed);

        // Wir können beim Iterieren nicht entfernen, also muss das so stattfinden.
        for(Credit credit : removal) {
            account.getCredits().remove(credit);
        }

        System.out.println(iban + " hat von allen Krediten " + Bank.INTEREST + "% Zinsen bezahlt.");
        System.out.println("Insgesamt Bezahlt: " + Main.MONEY_FORMAT.format(valuePayed));
        System.out.println("Kredite abgezahlt: " + removal.size());
        System.out.println("Neuer Kontostand: " + Main.MONEY_FORMAT.format(account.getBalance()));

        if(account.getCredits().isEmpty()) {
            System.out.println(iban + " keine weiteren Kredite.");
        } else {
            System.out.println(iban + " besitzt noch " + account.getCredits().size() + " weitere Kredite:");

            Iterator<Credit> creditIterator = account.getCredits().iterator();
            int index = 0;

            while (creditIterator.hasNext()) {
                Credit c = creditIterator.next();
                index++;

                System.out.println("[" + index + "] " + Main.MONEY_FORMAT.format(c.getCurrentValue()));
            }
        }
    }
}
