package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.CustomerMenu;
import carsharing.dao.CompanyDao;
import carsharing.dao.CustomerDao;
import carsharing.entity.Customer;

public class CustomerReturnCarCommand implements Command {
    CustomerDao customerDao = CustomerDao.get();
    CompanyDao companyDao = CompanyDao.get();
    private final Integer customerId;

    public CustomerReturnCarCommand(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean execute() {
        Customer customer = customerDao.getById(customerId);
        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
        } else {
            customer.setRentedCarId(null);
            customerDao.update(customer);
            System.out.println("You've returned a rented car!");
        }
        return true;
    }

    @Override
    public Context getContext() {
        return CustomerMenu.get(customerId);
    }
}
