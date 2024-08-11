package carsharing.command;

import carsharing.command.context.CustomerMenu;

public class CustomerCommand implements Command {
    private final Integer customerId;

    public CustomerCommand(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public Context getContext() {
        return CustomerMenu.get(customerId);
    }
}
