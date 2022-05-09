package com.bank.pro;

import com.bank.*;
import com.bank.command.ICommand;

import java.util.Iterator;

public class GrantCreditCommand implements ICommand {

    @Override
    public String getLabel() {
        return "grantcredit";
    }

    @Override
    public String getHelpMessage() {
        return "grantcredit <iban> <amount>";
    }

    @Override
    public void execute(String[] arguments) {
        if(arguments.length != 2) {
            System.out.println(getHelpMessage());
            return;
        }

        IBAN iban = new IBAN(arguments[0]);
        Account account = Main.bank.getAccount(iban);
        if(account == null) {
            System.out.println("Account '" + iban + "' wurde nicht gefunden.");
            return;
        }

        double value;
        try {
            value = Double.parseDouble(arguments[1].replace(",", "."));
        } catch (NumberFormatException exception) {
            System.out.println("Bitte gebe eine Zahl an.");
            return;
        }

        if(value < 0) {
            System.out.println("Bitte gebe nur positive Beträge an.");
            return;
        }

        account.addBalance(value);
        account.addCredit(value);
        System.out.println(iban + " wurde ein Kredit von " + Main.MONEY_FORMAT.format(value) + " gutgeschrieben.");
        System.out.println("Neuer Kontostand: " + Main.MONEY_FORMAT.format(account.getBalance()));

        System.out.println("Alle Kredite:");

        Iterator<Credit> creditIterator = account.getCredits().iterator();
        int index = 0;

        while (creditIterator.hasNext()) {
            double va = creditIterator.next().getCurrentValue();
            index++;

            System.out.println("[" + index + "] " + Main.MONEY_FORMAT.format(va));
        }
    }
}
