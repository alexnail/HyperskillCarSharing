package carsharing.command.context;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.customer.ChooseCompanyBackCommand;
import carsharing.command.customer.ChooseCompanyCar;
import carsharing.dao.CompanyDao;
import carsharing.entity.Company;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChooseCompanyMenu implements Context {

    private final static CompanyDao dao = CompanyDao.get();
    private final Integer customerId;

    private ChooseCompanyMenu(Integer customerId) {
        this.customerId = customerId;
    }

    public static ChooseCompanyMenu get(Integer customerId) {
        return new ChooseCompanyMenu(customerId);
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
            return new ChooseCompanyBackCommand(customerId);
        } else {
            return new ChooseCompanyCar(choice, customerId);
        }
    }
}
