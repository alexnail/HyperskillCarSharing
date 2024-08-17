package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.CustomerMenu;

public class ChooseCompanyBackCommand implements Command {

    private final Integer customerId;

    public ChooseCompanyBackCommand(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public Context getContext() {
        return CustomerMenu.get(customerId);
    }
}
