package carsharing.command;

import carsharing.command.context.MainMenu;
import carsharing.dao.CustomerDao;

import java.util.Scanner;

public class CreateCustomerCommand implements Command {

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the customer name:");
        String input = scanner.nextLine();
        CustomerDao.get().add(input);
        System.out.println("The customer was added!");
        return true;
    }

    @Override
    public Context getContext() {
        return MainMenu.get();
    }
}
