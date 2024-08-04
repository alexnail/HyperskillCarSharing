package carsharing.command;

import carsharing.command.context.ManagerMenu;

public class CompanyBackCommand implements Command {
    @Override
    public Context getContext() {
        return ManagerMenu.get();
    }
}
