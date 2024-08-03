package carsharing.command;

import carsharing.command.context.ManagerMenu;

public class CompanyListBackCommand implements Command {

    @Override
    public Context getContext() {
        return ManagerMenu.get();
    }
}
