package carsharing.command;

import carsharing.command.context.MainMenu;

public class LoginAsCustomerCommand implements Command {
    @Override
    public Context getContext() {
        return MainMenu.get();
    }
}
