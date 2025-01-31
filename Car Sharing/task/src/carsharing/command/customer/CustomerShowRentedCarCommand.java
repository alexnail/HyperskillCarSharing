package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.CustomerMenu;
import carsharing.dao.CarDao;
import carsharing.dao.CompanyDao;
import carsharing.dao.CustomerDao;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;

public class CustomerShowRentedCarCommand implements Command {
    private final static CustomerDao customerDao = CustomerDao.get();
    private final static CompanyDao companyDao = CompanyDao.get();
    private final static CarDao carDao = CarDao.get();
    private final Integer customerId;

    public CustomerShowRentedCarCommand(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean execute() {
        Customer customer = customerDao.getById(customerId);
        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
        } else {
            Car car = carDao.getById(customer.getRentedCarId());
            Company company = companyDao.getById(car.getCompanyId());
            System.out.printf("""
                    Your rented car:
                    %s
                    Company:
                    %s
                    """, car.getName(), company.getName());
        }
        return true;
    }

    @Override
    public Context getContext() {
        return CustomerMenu.get(customerId);
    }
}
