package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.dao.CompanyDao;
import carsharing.dao.CustomerDao;

public class CustomerRentCarCommand implements Command {
    CustomerDao customerDao = CustomerDao.get();
    CompanyDao companyDao = CompanyDao.get();

    private final Integer customerId;

    public CustomerRentCarCommand(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public Context getContext() {
        return null;
    }
}
