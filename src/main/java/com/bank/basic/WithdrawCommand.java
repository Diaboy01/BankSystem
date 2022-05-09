package com.bank.basic;

import com.bank.Account;
import com.bank.IBAN;
import com.bank.Main;
import com.bank.command.ICommand;

public class WithdrawCommand implements ICommand {
    @Override
    public String getLabel() {
        return "withdraw";
    }

    @Override
    public String getHelpMessage() {
        return "withdraw <iban> <amount>";
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

        double current = account.getBalance();
        account.removeBalance(value);

        System.out.println("Du hast " + Main.MONEY_FORMAT.format(value) + " vom Account " + iban + " abgehoben.");
        System.out.println("Davor: " + Main.MONEY_FORMAT.format(current));
        System.out.println("Jetzt: " + Main.MONEY_FORMAT.format(account.getBalance()));
    }
}
