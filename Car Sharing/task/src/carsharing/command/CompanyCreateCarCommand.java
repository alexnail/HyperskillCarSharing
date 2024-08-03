package carsharing.command;

import carsharing.command.context.CompanyMenu;
import carsharing.dao.CarDao;

import java.util.Scanner;

public class CompanyCreateCarCommand implements Command {
    private final Integer companyId;

    public CompanyCreateCarCommand(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter the car name:");
        Scanner scanner = new Scanner(System.in);
        String carName = scanner.nextLine();
        CarDao.get().add(companyId, carName);
        System.out.println("The car was added!");
        return true;
    }

    @Override
    public Context getContext() {
        return CompanyMenu.get(companyId);
    }
}
