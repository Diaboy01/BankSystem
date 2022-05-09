package com.bank.pro;

import com.bank.*;
import com.bank.command.ICommand;

import java.util.Iterator;

public class RepayCreditCommand implements ICommand {

    @Override
    public String getLabel() {
        return "repaycredit";
    }

    @Override
    public String getHelpMessage() {
        return "repaycredit <iban>";
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

        Credit credit = account.removeLastCredit();
        double value = credit.getCurrentValue();
        account.removeBalance(value);
        System.out.println(iban + " hat den letzten Kredit von " + Main.MONEY_FORMAT.format(value) + " bezahlt.");
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
