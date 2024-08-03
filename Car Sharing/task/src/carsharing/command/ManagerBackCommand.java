package carsharing.command;

import carsharing.command.context.MainMenu;

public class ManagerBackCommand implements Command {
    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public Context getContext() {
        return MainMenu.get();
    }
}
