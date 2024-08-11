package carsharing.command.context;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.customer.CustomerBackCommand;
import carsharing.command.customer.CustomerRentCarCommand;
import carsharing.command.customer.CustomerReturnCarCommand;
import carsharing.command.customer.CustomerShowRentedCarCommand;

public class CustomerMenu implements Context {
    private final Integer customerId;

    public CustomerMenu(Integer customerId) {
        this.customerId = customerId;
    }

    public static Context get(Integer customerId) {
        return new CustomerMenu(customerId);
    }

    @Override
    public void printPrompt() {
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
    }

    @Override
    public Command getCommand(Integer choice) {
        return switch (choice) {
            case 1 -> new CustomerRentCarCommand(customerId);
            case 2 -> new CustomerReturnCarCommand(customerId);
            case 3 -> new CustomerShowRentedCarCommand(customerId);
            case 0 -> new CustomerBackCommand();
            default -> throw new IllegalArgumentException("Unexpected choice: " + choice);
        };
    }
}
