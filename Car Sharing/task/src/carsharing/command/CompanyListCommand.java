package carsharing.command;

import carsharing.command.context.ManagerMenu;
import carsharing.dao.CompanyDao;
import carsharing.entity.Company;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CompanyListCommand implements Command {
    private final static CompanyDao dao = CompanyDao.get();

    @Override
    public boolean execute() {
        // get companies from DB
        List<Company> all = dao.getAll();
        AtomicInteger idx =  new AtomicInteger(1);
        if (!all.isEmpty()) {
            System.out.println("Company list:");
            all.stream()
                    .sorted(Comparator.comparing(Company::getId))
                    .forEach(c -> System.out.println(idx.getAndIncrement() + ". " + c.getName()));
        } else {
            System.out.println("The company list is empty!");
        }
        return true;
    }

    @Override
    public Context getContext() {
        return ManagerMenu.get();
    }
}
