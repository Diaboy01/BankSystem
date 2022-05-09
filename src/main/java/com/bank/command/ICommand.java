package com.bank.command;

public interface ICommand {

    String getLabel();
    String getHelpMessage();

    void execute(String[] arguments);

}
