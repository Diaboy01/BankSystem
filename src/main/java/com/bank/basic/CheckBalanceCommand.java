package com.bank.basic;

import com.bank.Account;
import com.bank.Bank;
import com.bank.IBAN;
import com.bank.Main;
import com.bank.command.ICommand;

public class CheckBalanceCommand implements ICommand {
    @Override
    public String getLabel() {
        return "balance";
    }

    @Override
    public String getHelpMessage() {
        return "balance <iban>";
    }

    @Override
    public void execute(String[] arguments) {
        if(arguments.length != 1) {
            System.out.println(getHelpMessage());
            return;
        }

        IBAN source = new IBAN(arguments[0]);
        Account sourceAccount = Main.bank.getAccount(source);

        if(sourceAccount == null) {
            System.out.println("Account '" + source + "' wurde nicht gefunden.");
            return;
        }

        double current = sourceAccount.getBalance();
            System.out.println("Balance: " + Main.MONEY_FORMAT.format(current));
            return;
        }
    }
