package carsharing.command;

import carsharing.command.context.CompanyListMenu;

public class CompanyBackCommand implements Command {
    @Override
    public Context getContext() {
        return CompanyListMenu.get();
    }
}
