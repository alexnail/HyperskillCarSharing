package carsharing.command;

import carsharing.command.context.MainMenu;

public class ManagerBackCommand implements Command {
    @Override
    public Context getContext() {
        return MainMenu.get();
    }
}
