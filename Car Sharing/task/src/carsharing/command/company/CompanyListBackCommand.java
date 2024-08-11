package carsharing.command.company;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.ManagerMenu;

public class CompanyListBackCommand implements Command {

    @Override
    public Context getContext() {
        return ManagerMenu.get();
    }
}
