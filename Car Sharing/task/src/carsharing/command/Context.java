package carsharing.command;

public interface Context {
    void printPrompt();

    Command getCommand(Integer choice);
}
