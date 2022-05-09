package com.bank.command;

import com.bank.basic.*;

import java.util.HashMap;

public class CommandRegistry {

    private static final CommandRegistry REGISTRY = new CommandRegistry();
    public static CommandRegistry getInstance() {
        return REGISTRY;
    }

    private final HashMap<String, ICommand> commands;
    private CommandRegistry() {
        commands = new HashMap<>();
        registerDefaults();
        new Console().start();
    }

    public HashMap<String, ICommand> getCommands() {
        return commands;
    }

    private void registerDefaults() {
        // Hier registrieren wir unsere Befehle.
        registerCommand(new HelpCommand());
        registerCommand(new CreateAccountCommand());
        registerCommand(new DepositCommand());
        registerCommand(new WithdrawCommand());
        registerCommand(new TransferCommand());

        // premium version
        try {
            Class<?> grantCommand = Class.forName("com.bank.pro.GrantCreditCommand");
            Class<?> repayCommand = Class.forName("com.bank.pro.RepayCreditCommand");
            Class<?> payInterestCommand = Class.forName("com.bank.pro.PayInterestCommand");

            registerCommand((ICommand) grantCommand.newInstance());
            registerCommand((ICommand) repayCommand.newInstance());
            registerCommand((ICommand) payInterestCommand.newInstance());

            System.out.println("Danke für den Kauf der PRO Version!");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ignore) {
        }
    }

    public boolean existsCommand(String label) {
        return commands.containsKey(label);
    }

    public void registerCommand(ICommand command) {
        commands.put(command.getLabel(), command);
    }

    public ICommand getCommand(String label) {
        return commands.get(label);
    }
}
