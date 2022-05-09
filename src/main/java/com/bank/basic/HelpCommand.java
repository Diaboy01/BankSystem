package com.bank.basic;

import com.bank.command.CommandRegistry;
import com.bank.command.ICommand;

public class HelpCommand implements ICommand {

    @Override
    public String getLabel() {
        return "help";
    }

    @Override
    public String getHelpMessage() {
        return "help";
    }

    @Override
    public void execute(String[] arguments) {
        if(arguments.length != 0) {
            System.out.println(getHelpMessage());
            return;
        }

        System.out.println("Hilfeliste:");
        for(ICommand command : CommandRegistry.getInstance().getCommands().values()) {
            System.out.println(command.getHelpMessage());
        }
    }
}
