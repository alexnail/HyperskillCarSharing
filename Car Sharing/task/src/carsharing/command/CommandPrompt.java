package carsharing.command;

import carsharing.command.context.MainMenu;

import java.util.Scanner;

import static carsharing.Util.trace;

public class CommandPrompt {
    private final Scanner scanner = new Scanner(System.in);;

    private Context context;

    public CommandPrompt() {
        context = MainMenu.get();
    }

    public Command command() {
        context.printPrompt();
        Integer choice = Integer.parseInt(scanner.nextLine());
        return context.getCommand(choice);
    }

    public void updateContext(Command command) {
        Context updated = command.getContext();
        if (updated != null) {
            trace("Context updated: [%s]".formatted(updated.getClass().getSimpleName()));
        } else {
            trace("No context provided by [%s] command. Setting to [MainMenu].".formatted(command.getClass().getSimpleName()));
            updated = MainMenu.get();
        }
        this.context = updated;
    }
}
