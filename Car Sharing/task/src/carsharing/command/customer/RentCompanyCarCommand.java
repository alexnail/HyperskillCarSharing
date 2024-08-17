package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.CustomerMenu;
import carsharing.dao.CarDao;
import carsharing.dao.CompanyDao;
import carsharing.dao.CustomerDao;
import carsharing.entity.Car;
import carsharing.entity.Customer;

public class RentCompanyCarCommand implements Command {

    private final static CarDao carDao = CarDao.get();
    private final static CustomerDao customerDao = CustomerDao.get();
    private final static CompanyDao companyDao = CompanyDao.get();

    private final String carName;
    private final Integer companyId;
    private final Integer customerId;

    public RentCompanyCarCommand(String carName, Integer companyId, Integer customerId) {
        this.carName = carName;
        this.companyId = companyId;
        this.customerId = customerId;
    }

    @Override
    public boolean execute() {
        Car car = carDao.getByName(carName);
        Customer customer = customerDao.getById(customerId);
        customer.setRentedCarId(car.getId());
        customerDao.update(customer);
        System.out.printf("You rented '%s'%n", car.getName());
        return true;
    }

    @Override
    public Context getContext() {
        return CustomerMenu.get(customerId);
    }
}
