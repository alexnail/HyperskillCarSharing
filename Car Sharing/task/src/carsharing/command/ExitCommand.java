package carsharing.command;

public class ExitCommand implements Command {

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public Context getContext() {
        return null;
    }
}
