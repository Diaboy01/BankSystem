package com.bank.basic;

import com.bank.Account;
import com.bank.Bank;
import com.bank.IBAN;
import com.bank.Main;
import com.bank.command.ICommand;

public class TransferCommand implements ICommand {
    @Override
    public String getLabel() {
        return "transfer";
    }

    @Override
    public String getHelpMessage() {
        return "transfer <target-iban> <source-iban> <amount>";
    }

    @Override
    public void execute(String[] arguments) {
        if(arguments.length != 3) {
            System.out.println(getHelpMessage());
            return;
        }

        IBAN target = new IBAN(arguments[0]), source = new IBAN(arguments[1]);
        Account targetAccount = Main.bank.getAccount(target), sourceAccount = Main.bank.getAccount(source);

        if(targetAccount == null) {
            System.out.println("Empfängeraccount '" + target + "' wurde nicht gefunden.");
            return;
        }

        if(sourceAccount == null) {
            System.out.println("Senderaccount '" + source + "' wurde nicht gefunden.");
            return;
        }

        double value;
        try {
            value = Double.parseDouble(arguments[2].replace(",", "."));
        } catch (NumberFormatException exception) {
            System.out.println("Bitte gebe eine Zahl an.");
            return;
        }

        if(value < 0) {
            System.out.println("Bitte gebe nur positive Beträge an.");
            return;
        }

        double current = targetAccount.getBalance();
        if(value - current < Bank.MIN_BALANCE) {
            System.out.println("Das transferieren von " + Main.MONEY_FORMAT.format(value) + " von " + source + " zu " + target + " würde das Restguthaben von " + Main.MONEY_FORMAT.format(Bank.MIN_BALANCE) + " unterschreiten.");
            return;
        }

        sourceAccount.removeBalance(value);
        targetAccount.addBalance(value);

        System.out.println("'" + source + "' hat " + Main.MONEY_FORMAT.format(value) + " von '" + target + "' erhalten.");
        System.out.println("Neuer Kontostand von " + source + " (Sender): " + Main.MONEY_FORMAT.format(sourceAccount.getBalance()));
        System.out.println("Neuer Kontostand von " + target + " (Empfänger): " + Main.MONEY_FORMAT.format(targetAccount.getBalance()));
    }
}
