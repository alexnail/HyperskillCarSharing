package carsharing.command;

import carsharing.command.context.CustomerListMenu;
import carsharing.command.context.MainMenu;
import carsharing.dao.CustomerDao;

public class LoginAsCustomerCommand implements Command {
    private static final CustomerDao dao = CustomerDao.get();
    private Context context;

    @Override
    public boolean execute() {
        if (dao.getAll().isEmpty()) {
            System.out.println("The customer list is empty!");
            context = MainMenu.get();
        } else {
            context = CustomerListMenu.get();
        }
        return true;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
