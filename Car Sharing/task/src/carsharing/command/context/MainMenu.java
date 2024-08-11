package carsharing.command.context;

import carsharing.command.*;

public class MainMenu implements Context {
    private final static MainMenu INSTANCE = new MainMenu();

    private MainMenu() {
    }

    public static Context get() {
        return INSTANCE;
    }

    @Override
    public void printPrompt() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
    }

    @Override
    public Command getCommand(Integer choice) {
        return switch (choice) {
            case 1 -> new LoginAsManagerCommand();
            case 2 -> new LoginAsCustomerCommand();
            case 3 -> new CreateCustomerCommand();
            case 0 -> new ExitCommand();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }
}
