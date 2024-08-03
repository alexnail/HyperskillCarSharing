package carsharing.command;

public interface Command {
    default boolean execute() {
        return true;
    };

    Context getContext();
}
