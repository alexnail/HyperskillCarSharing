package carsharing.command.context;

import carsharing.command.Command;
import carsharing.command.CompanyCommand;
import carsharing.command.CompanyListBackCommand;
import carsharing.command.Context;
import carsharing.dao.CompanyDao;
import carsharing.entity.Company;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class CompanyListMenu implements Context {

    private final static CompanyListMenu INSTANCE = new CompanyListMenu();
    private final static CompanyDao dao = CompanyDao.get();

    private CompanyListMenu() {
    }

    public static CompanyListMenu get() {
        return INSTANCE;
    }

    @Override
    public void printPrompt() {
        List<Company> all = dao.getAll();
        AtomicInteger idx = new AtomicInteger(1);
        if (!all.isEmpty()) {
            all.stream()
                    .sorted(Comparator.comparing(Company::getId))
                    .forEach(c -> System.out.println(idx.getAndIncrement() + ". " + c.getName()));
            System.out.println("0. Back");
        } else {
            System.out.println("The company list is empty!");
        }
    }

    @Override
    public Command getCommand(Integer choice) {
        if (choice == 0) {
            return new CompanyListBackCommand();
        } else {
            return new CompanyCommand(choice);
        }
    }
}
