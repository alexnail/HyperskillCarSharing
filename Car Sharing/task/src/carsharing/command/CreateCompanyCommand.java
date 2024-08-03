package carsharing.command;

import carsharing.command.context.ManagerMenu;
import carsharing.dao.CompanyDao;

import java.util.Scanner;

public class CreateCompanyCommand implements Command {

    Scanner scanner = new Scanner(System.in);
    @Override
    public boolean execute() {
        System.out.println("Enter the company name:");
        String input = scanner.nextLine();
        CompanyDao.get().add(input);
        System.out.println("The company was created!");
        return true;
    }

    @Override
    public Context getContext() {
        return ManagerMenu.get();
    }
}
