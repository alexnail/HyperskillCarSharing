package carsharing.command;

import carsharing.command.context.MainMenu;

public class CustomerListBackCommand implements Command {
    @Override
    public Context getContext() {
        return MainMenu.get();
    }
}
