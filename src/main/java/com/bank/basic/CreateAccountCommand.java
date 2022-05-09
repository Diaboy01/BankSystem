package com.bank.basic;

import com.bank.Account;
import com.bank.IBAN;
import com.bank.Main;
import com.bank.command.ICommand;

import java.util.Locale;

public class CreateAccountCommand implements ICommand {

    @Override
    public String getLabel() {
        return "createaccount";
    }

    @Override
    public String getHelpMessage() {
        return getLabel() + " <country>";
    }

    @Override
    public void execute(String[] arguments) {
        if(arguments.length != 1) {
            System.out.println(getHelpMessage());
            return;
        }

        String country = arguments[0].toUpperCase(Locale.ROOT);
        // TODO: vielleicht abfragen ob das Land valide ist?
        Account account = Main.bank.createAccount(country);
        System.out.println("Neuen Account angelegt: " + account.getIBAN());
    }
}
