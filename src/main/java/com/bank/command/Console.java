package com.bank.command;

import java.util.Scanner;

public class Console extends Thread {

    private final Scanner scanner;
    public Console() {
        super.setName("[I/O]");
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] commandSplitted = command.split(" ");

            String label = commandSplitted[0];
            ICommand iCommand = CommandRegistry.getInstance().getCommand(label);

            if(iCommand == null) {
                System.out.println("Befehl '" + label + "' wurde nicht gefunden.");
                continue;
            }

            String[] arguments = new String[commandSplitted.length - 1]; // eins kleiner, weil wir das label bereits verarbeitet haben.
            for(int i = 1; i < commandSplitted.length; i++) {
                arguments[i - 1] = commandSplitted[i]; // setze das vorherige Element auf das Element in commandSplitted, wir gehen ein Element zurück, weil wir eins kleiner sind und bei 0 und nicht bei 1 anfangen.
            }

            iCommand.execute(arguments);
        }
    }
}
