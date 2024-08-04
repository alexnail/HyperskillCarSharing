package carsharing.command;

import carsharing.command.context.CompanyListMenu;
import carsharing.command.context.ManagerMenu;
import carsharing.dao.CompanyDao;

public class ManagerCompanyListCommand implements Command {
    private final static CompanyDao dao = CompanyDao.get();
    private Context context;

    @Override
    public boolean execute() {
        if (dao.getAll().isEmpty()) {
            System.out.println("The company list is empty!");
            context = ManagerMenu.get();
        } else {
            System.out.println("Choose the company");
            context = CompanyListMenu.get();
        }
        return true;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
