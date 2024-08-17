package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.ChooseCompanyMenu;
import carsharing.command.context.CustomerMenu;
import carsharing.dao.CompanyDao;
import carsharing.dao.CustomerDao;
import carsharing.entity.Customer;

public class CustomerRentCarCommand implements Command {
    private final static CustomerDao customerDao = CustomerDao.get();
    private final static CompanyDao companyDao = CompanyDao.get();

    private Context context;

    private final Integer customerId;

    public CustomerRentCarCommand(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean execute() {
        Customer customer = customerDao.getById(customerId);
        if (customer.getRentedCarId() == null) {
            System.out.println("Choose a company:");
            context = ChooseCompanyMenu.get(customerId);
        } else {
            System.out.println("You've already rented a car!");
            context = CustomerMenu.get(customerId);
        }
        return true;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
