package carsharing.command;

import carsharing.command.context.MainMenu;

import java.util.Scanner;

public class CommandPrompt {
    private final Scanner scanner;

    private Context context;

    public CommandPrompt(Scanner scanner) {
        this.scanner = scanner;
        context = MainMenu.get();
    }

    public Command command() {
        context.printPrompt();
        Integer choice = Integer.parseInt(scanner.nextLine());
        Command command = context.getCommand(choice);
        context = command.getContext() != null ? command.getContext() : MainMenu.get();

        return command;
    }
}
