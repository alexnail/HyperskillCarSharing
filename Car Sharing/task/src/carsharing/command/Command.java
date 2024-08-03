package carsharing.command;

public interface Command {
    boolean execute();

    Context getContext();
}
