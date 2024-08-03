package carsharing.command;

import carsharing.command.context.ManagerMenu;

public class LoginAsManagerCommand implements Command {

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public Context getContext() {
        return ManagerMenu.get();
    }
}
