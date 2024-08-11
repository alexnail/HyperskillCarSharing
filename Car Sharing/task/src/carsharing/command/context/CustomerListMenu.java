package carsharing.command.context;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.CustomerCommand;
import carsharing.command.CustomerListBackCommand;
import carsharing.dao.CustomerDao;
import carsharing.entity.Customer;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerListMenu implements Context {
    private final static CustomerListMenu INSTANCE = new CustomerListMenu();
    private final static CustomerDao dao = CustomerDao.get();

    private CustomerListMenu() {
    }

    public static CustomerListMenu get() {
        return INSTANCE;
    }

    @Override
    public void printPrompt() {
        List<Customer> all =  dao.getAll();
        AtomicInteger idx = new AtomicInteger(1);
        if (!all.isEmpty()) {
            all.stream()
                    .sorted(Comparator.comparing(Customer::getId))
                    .forEach(c -> System.out.println(idx.getAndIncrement() + ". " + c.getName()));
            System.out.println("0. Back");
        } else {
            System.out.println("The customer list is empty!");
        }
    }

    @Override
    public Command getCommand(Integer choice) {
        if (choice == 0) {
            return new CustomerListBackCommand();
        } else {
            return new CustomerCommand(choice);
        }
    }
}
