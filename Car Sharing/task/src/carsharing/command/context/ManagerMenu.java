package carsharing.command.context;

import carsharing.command.*;

public class ManagerMenu implements Context {

    private static final ManagerMenu INSTANCE = new ManagerMenu();

    private ManagerMenu() {}

    public static Context get() {
        return INSTANCE;
    }

    @Override
    public void printPrompt() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    @Override
    public Command getCommand(Integer choice) {
        return switch (choice) {
            case 1 -> new ManagerCompanyListCommand();
            case 2 -> new CreateCompanyCommand();
            case 0 -> new ManagerBackCommand();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }
}
