package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.MainMenu;

public class CustomerListBackCommand implements Command {
    @Override
    public Context getContext() {
        return MainMenu.get();
    }
}
